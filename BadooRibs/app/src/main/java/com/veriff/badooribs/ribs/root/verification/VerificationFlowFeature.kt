package com.veriff.badooribs.ribs.root.verification

import com.badoo.mvicore.element.Actor
import com.badoo.mvicore.element.Reducer
import com.badoo.mvicore.feature.ActorReducerFeature
import com.veriff.badooribs.domain.logic.verification.MatchDocumentTypeUseCase
import com.veriff.badooribs.domain.logic.verification.ResolveDocumentTypeUseCase
import com.veriff.badooribs.domain.model.VerificationArtifact
import com.veriff.badooribs.domain.model.VerificationScenario
import com.veriff.badooribs.domain.model.VerificationTarget
import com.veriff.badooribs.ribs.root.verification.VerificationFlowFeature.*
import com.veriff.badooribs.util.*
import io.reactivex.Observable
import java.util.*

internal class VerificationFlowFeature(
    val deps: VerificationFlowRib.Dependency,
    scenario: VerificationScenario,
) :
    ActorReducerFeature<Wish, Effect, State, Nothing>(
        initialState = State(
            targets = LinkedList(scenario.targets),
            activeTargetIndex = 0,
            artifacts = LinkedList(),
            presentationStep = State.PresentationStep.Idle
        ),
        actor = ActorImpl(deps),
        reducer = ReducerImpl()
    ) {

    data class State(
        val targets: LinkedList<VerificationTarget>,
        val activeTargetIndex: Int,
        val artifacts: LinkedList<VerificationArtifact>,
        val presentationStep: PresentationStep,
    ) {
        sealed class PresentationStep {
            object Idle : PresentationStep() // todo?

            object Processing : PresentationStep()

            class TakePhoto(val target: VerificationTarget.Photo) : PresentationStep()

            class UndefinedDocumentType(val artifact: VerificationArtifact.Photo) :
                PresentationStep()

            class WrongDocumentType(val artifact: VerificationArtifact.Photo) : PresentationStep()

            object Finish : PresentationStep()

            object Fail : PresentationStep()

            override fun toString(): String = classSimpleName
        }
    }

    sealed class Wish {
        class ProcessArtifact(val artifact: VerificationArtifact) : Wish()
        class UpdateTargets(val targetUpdate: List<VerificationTarget>) : Wish()
    }

    sealed class Effect {
        object NextStep : Effect()
        class PushArtifact(val artifact: VerificationArtifact) : Effect()
        class ReplaceArtifact(val artifact: VerificationArtifact) : Effect()
        class DocumentTypeResolved(val targetUpdate: List<VerificationTarget>) : Effect()

        class WrongDocumentType(val artifact: VerificationArtifact.Photo) : Effect()
        class UndefinedDocumentType(val artifact: VerificationArtifact.Photo) : Effect()
    }

    private class ActorImpl(
        val deps: VerificationFlowRib.Dependency,
    ) : Actor<State, Wish, Effect> {
        private val resolveDocumentTypeUseCase =
            ResolveDocumentTypeUseCase(deps.verificationRepository)
        private val matchDocumentTypeUseCase = MatchDocumentTypeUseCase(deps.verificationRepository)

        override fun invoke(state: State, wish: Wish): Observable<out Effect> {
            return when (wish) {
                is Wish.ProcessArtifact -> processArtifact(wish.artifact)
                is Wish.UpdateTargets -> Effect.DocumentTypeResolved(wish.targetUpdate).toObservable()
            }
        }

        private fun processArtifact(artifact: VerificationArtifact): Observable<out Effect> {
            return when (artifact) {
                is VerificationArtifact.Photo -> processPhoto(artifact)
                is VerificationArtifact.Nfc -> Effect.NextStep.toObservable()
            }
        }

        private fun processPhoto(artifact: VerificationArtifact.Photo): Observable<out Effect> {
            return when (artifact.target) {
                is VerificationTarget.Photo.UnspecifiedDocument -> resolveDocumentType(artifact)
                is VerificationTarget.Photo.Document -> matchDocumentType(artifact)
                else -> Effect.NextStep.toObservable()
            }
        }

        private fun resolveDocumentType(artifact: VerificationArtifact.Photo): Observable<out Effect> {
            return resolveDocumentTypeUseCase.execute(artifact)
                .map<Effect> { newTargets -> Effect.DocumentTypeResolved(newTargets) }
                .onErrorReturn { Effect.UndefinedDocumentType(artifact) }
                .observeOn(deps.ui)
                .startWith(Effect.PushArtifact(artifact))
        }

        private fun matchDocumentType(artifact: VerificationArtifact.Photo): Observable<out Effect> {
            return matchDocumentTypeUseCase.execute(artifact)
                .map { matches ->
                    if (matches) {
                        Effect.NextStep
                    } else {
                        Effect.WrongDocumentType(artifact)
                    }
                }
                .observeOn(deps.ui)
                .startWith(Effect.PushArtifact(artifact))
        }
    }

    private class ReducerImpl : Reducer<State, Effect> {
        override fun invoke(state: State, effect: Effect): State {
            return when (effect) {
                is Effect.NextStep -> getNextState(state)

                is Effect.PushArtifact -> state.copy(
                    artifacts = state.artifacts.append(effect.artifact),
                    presentationStep = State.PresentationStep.Processing
                )

                is Effect.ReplaceArtifact -> state.copy(
                    artifacts = state.artifacts.replaceLastBy(effect.artifact)
                )

                is Effect.UndefinedDocumentType -> state.copy(
                    presentationStep = State.PresentationStep.UndefinedDocumentType(effect.artifact)
                )

                is Effect.WrongDocumentType -> state.copy(
                    presentationStep = State.PresentationStep.Fail
                )

                is Effect.DocumentTypeResolved -> {
                    val targets = state.targets.replaceAtPosition(
                        position = state.activeTargetIndex,
                        items = effect.targetUpdate)
                    state.copy(
                        targets = targets,
                        activeTargetIndex = state.activeTargetIndex + 1,
                        presentationStep = getStepAtIndex(targets, state.activeTargetIndex + 1)
                    )
                }
            }
        }

        private fun getNextState(state: State): State {
            val nextIndex = state.activeTargetIndex + 1
            var nextStep = getStepAtIndex(state.targets, nextIndex)

            return state.copy(
                activeTargetIndex = nextIndex,
                presentationStep = nextStep
            )
        }

        private fun getStepAtIndex(
            targets: List<VerificationTarget>,
            index: Int,
        ): State.PresentationStep {
            return if (index >= targets.size) {
                State.PresentationStep.Finish
            } else {
                when (val target = targets[index]) {
                    is VerificationTarget.Photo -> State.PresentationStep.TakePhoto(target)
                    is VerificationTarget.Nfc -> State.PresentationStep.Finish
                }
            }
        }
    }
}