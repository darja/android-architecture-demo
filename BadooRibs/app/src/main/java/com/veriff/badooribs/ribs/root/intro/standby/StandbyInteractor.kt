package com.veriff.badooribs.ribs.root.intro.standby

import androidx.lifecycle.Lifecycle
import com.badoo.binder.using
import com.badoo.mvicore.android.lifecycle.startStop
import com.badoo.ribs.clienthelper.interactor.Interactor
import com.badoo.ribs.core.modality.BuildParams
import com.veriff.badooribs.ribs.root.intro.standby.StandbyRib.Output
import com.veriff.badooribs.ribs.root.intro.standby.StandbyView.Event
import com.veriff.badooribs.util.LogConsumer

internal class StandbyInteractor(
    private val feature: StandbyFeature,
    private val buildParams: BuildParams<*>,
) : Interactor<StandbyRib, StandbyView>(buildParams) {
    override fun onViewCreated(view: StandbyView, viewLifecycle: Lifecycle) {
        viewLifecycle.startStop {
            bind(view to rib.output using ViewEventToOutput(feature))
            bind(view to feature using ViewEventToFeature)
            bind(feature to view using FeatureToViewModel)
            bind(feature to LogConsumer("Standby", "StandbyFeature"))
        }
    }

    private object FeatureToViewModel : (StandbyFeature.State) -> StandbyView.ViewModel? {
        override fun invoke(state: StandbyFeature.State): StandbyView.ViewModel? {
            return when (state) {
                is StandbyFeature.State.Loading -> StandbyView.ViewModel.Loading
                is StandbyFeature.State.Loaded ->
                    StandbyView.ViewModel.Loaded(state.configs, state.selectedIndex)
            }
        }
    }

    private object ViewEventToFeature : (Event) -> StandbyFeature.Wish? {
        override fun invoke(event: Event): StandbyFeature.Wish? =
            when (event) {
                is Event.ScenarioSelected -> StandbyFeature.Wish.SelectScenario(event.position)
                else -> null
            }
    }

    private class ViewEventToOutput(val feature: StandbyFeature) : (Event) -> Output? {
        override fun invoke(event: Event): Output? =
            when (event) {
                is Event.StartClicked -> (feature.state as? StandbyFeature.State.Loaded)?.let {
                    Output.VerificationStart(it.configs[it.selectedIndex].scenario)

                }
                else -> null
            }
    }
}