package com.veriff.badooribs.ribs.root.intro.standby

import com.badoo.mvicore.element.Actor
import com.badoo.mvicore.element.Bootstrapper
import com.badoo.mvicore.element.Reducer
import com.badoo.mvicore.feature.ActorReducerFeature
import com.veriff.badooribs.domain.logic.verification.ApplyVerificationConfigUseCase
import com.veriff.badooribs.domain.logic.verification.GetVerificationConfigsUseCase
import com.veriff.badooribs.domain.model.MockVerificationConfig
import com.veriff.badooribs.util.toObservable
import io.reactivex.Observable

class StandbyFeature(
    val deps: StandbyRib.Dependency,
) : ActorReducerFeature<StandbyFeature.Wish, StandbyFeature.Effect, StandbyFeature.State, Nothing>(
    initialState = State.Loaded(emptyList(), -1),
    bootstrapper = BootstrapperImpl(),
    actor = ActorImpl(deps),
    reducer = ReducerImpl()
) {
    sealed class State {
        object Loading : State()

        data class Loaded(
            val configs: List<MockVerificationConfig>,
            val selectedIndex: Int,
        ) : State()
    }

    sealed class Wish {
        object Refresh : Wish()
        class SelectScenario(val index: Int): Wish()
    }

    sealed class Effect {
        object LoadingStarted: Effect()
        class LoadingFinished(val configs: List<MockVerificationConfig>): Effect()
        class ScenarioSelected(val configs: List<MockVerificationConfig>, val index: Int): Effect()
    }

    class BootstrapperImpl : Bootstrapper<Wish> {
        override fun invoke(): Observable<Wish> = Wish.Refresh.toObservable()
    }

    private class ActorImpl(
        val deps: StandbyRib.Dependency
    ) : Actor<State, Wish, Effect> {

        val getConfigsUseCase = GetVerificationConfigsUseCase(deps.scenariosRepository)
        val applyConfigUseCase = ApplyVerificationConfigUseCase(deps.verificationRepository)

        private fun noEffect() = Observable.empty<Effect>()

        override fun invoke(state: State, wish: Wish): Observable<out Effect> {
            return when (wish) {
                is Wish.Refresh ->
                    if (state is State.Loaded) {
                        getConfigsUseCase.execute()
                            .map<Effect> { Effect.LoadingFinished(it) }
                            .startWith(Effect.LoadingStarted)
                            .observeOn(deps.ui)
                    } else {
                        noEffect()
                    }

                is Wish.SelectScenario ->
                    if (state is State.Loaded) {
                        applyConfigUseCase.execute(state.configs[wish.index])
                            .andThen(Effect.ScenarioSelected(state.configs, wish.index).toObservable())
                            .observeOn(deps.ui)
                    } else {
                        noEffect()
                    }
            }
        }
    }

    private class ReducerImpl : Reducer<State, Effect> {
        override fun invoke(state: State, effect: Effect): State {
            return when (effect) {
                is Effect.LoadingStarted -> State.Loading
                is Effect.LoadingFinished -> State.Loaded(effect.configs, 0)
                is Effect.ScenarioSelected -> State.Loaded(effect.configs, effect.index)
            }
        }
    }
}