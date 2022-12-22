package com.veriff.badooribs.ribs.root.verification

import androidx.lifecycle.Lifecycle
import com.badoo.binder.using
import com.badoo.mvicore.android.lifecycle.createDestroy
import com.badoo.mvicore.android.lifecycle.startStop
import com.badoo.ribs.clienthelper.childaware.whenChildBuilt
import com.badoo.ribs.clienthelper.interactor.Interactor
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.routing.source.backstack.BackStack
import com.badoo.ribs.routing.source.backstack.operation.pushOverlay
import com.badoo.ribs.routing.source.backstack.operation.replace
import com.jakewharton.rxrelay2.Relay
import com.veriff.badooribs.domain.model.VerificationTarget
import com.veriff.badooribs.ribs.checkpermission.CheckPermissionRib
import com.veriff.badooribs.ribs.error.ErrorPayload
import com.veriff.badooribs.ribs.root.verification.VerificationFlowFeature.State.PresentationStep
import com.veriff.badooribs.ribs.root.verification.VerificationFlowRib.Configuration
import com.veriff.badooribs.ribs.root.verification.resolvedocument.ResolveDocumentRib
import com.veriff.badooribs.ribs.root.verification.takephoto.TakePhotoRib
import com.veriff.badooribs.util.LogConsumer
import io.reactivex.functions.Consumer

internal class VerificationFlowInteractor(
    private val backStack: BackStack<Configuration>,
    private val feature: VerificationFlowFeature,
    buildParams: BuildParams<*>,
) : Interactor<VerificationFlowRib, Nothing>(
    buildParams = buildParams
) {
    override fun onCreate(nodeLifecycle: Lifecycle) {
        nodeLifecycle.createDestroy {}

        nodeLifecycle.startStop {
            bind(feature to FeatureRoutingListener())
            bind(feature to LogConsumer("VerificationFlow", "Feature state"))
        }

        whenChildBuilt<TakePhotoRib>(nodeLifecycle) { commonLifecycle, child ->
            commonLifecycle.createDestroy {
                bind(feature to StateToTakePhotoInput(child.input))
                bind(child.output to feature using TakePhotoToWish)

                bind(child.output to LogConsumer("VerificationFlow", "TakePhoto event"))
            }
        }

        whenChildBuilt<CheckPermissionRib>(nodeLifecycle) { commonLifecycle, child ->
            commonLifecycle.createDestroy {
                bind(child.output to CheckPermissionRoutingListener())
                bind(child.output to LogConsumer("VerificationFlow", "CheckPermission event"))
            }
        }

        whenChildBuilt<ResolveDocumentRib>(nodeLifecycle) {
            commonLifecycle, child ->
            commonLifecycle.createDestroy {
                bind(child.output to ResolveDocumentListener())
                bind(child.output to LogConsumer("VerificationFlow", "ResolveDocument event"))
            }
        }
    }

    private object TakePhotoToWish : (TakePhotoRib.Output) -> VerificationFlowFeature.Wish? {
        override fun invoke(event: TakePhotoRib.Output): VerificationFlowFeature.Wish =
            when (event) {
                is TakePhotoRib.Output.ArtifactProduced ->
                    VerificationFlowFeature.Wish.ProcessArtifact(event.artifact)
            }
    }

    private class StateToTakePhotoInput(private val input: Relay<TakePhotoRib.Input>) : Consumer<VerificationFlowFeature.State?> {
        override fun accept(state: VerificationFlowFeature.State?) {
            when (val step = state?.presentationStep) {
                is PresentationStep.TakePhoto -> input.accept(TakePhotoRib.Input.ChangeTarget(step.target))
                else -> Unit
            }
        }
    }

    private inner class FeatureRoutingListener: Consumer<VerificationFlowFeature.State> {
        override fun accept(event: VerificationFlowFeature.State?) {
            when (val step = event?.presentationStep) {
                is PresentationStep.WrongDocumentType ->
                    backStack.pushOverlay(Configuration.WrongDocumentType(step.artifact))

                is PresentationStep.UndefinedDocumentType ->
                    backStack.pushOverlay(Configuration.UndefinedDocumentType(step.artifact))

                is PresentationStep.Finish ->
                    rib.output.accept(VerificationFlowRib.Output.Success)

                is PresentationStep.Fail ->
                    rib.output.accept(VerificationFlowRib.Output.Fail)

                else -> Unit
            }
        }
    }

    private inner class ResolveDocumentListener: Consumer<ResolveDocumentRib.Output> {
        override fun accept(event: ResolveDocumentRib.Output?) {
            when (event) {
                is ResolveDocumentRib.Output.Finish -> {
                    feature.accept(VerificationFlowFeature.Wish.UpdateTargets(event.targetUpdate))
                    backStack.popOverlay()
                }
            }
        }
    }

    private inner class CheckPermissionRoutingListener: Consumer<CheckPermissionRib.Output> {
        override fun accept(event: CheckPermissionRib.Output?) {
            when (event) {
                is CheckPermissionRib.Output.Granted -> attachVerificationStep()
                is CheckPermissionRib.Output.Denied -> attachPermissionError()
            }
        }

        private fun attachVerificationStep() {
            when (val firstTarget = feature.state.targets[0]) {
                is VerificationTarget.Photo -> backStack.replace(Configuration.TakePhoto(firstTarget))
                is VerificationTarget.Nfc -> Unit // todo
            }
        }

        private fun attachPermissionError() {
            backStack.replace(
                Configuration.Error(ErrorPayload.PermissionError)
            )
        }
    }
}