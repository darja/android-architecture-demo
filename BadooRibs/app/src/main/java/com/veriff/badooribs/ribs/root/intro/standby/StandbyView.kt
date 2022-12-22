package com.veriff.badooribs.ribs.root.intro.standby

import com.badoo.ribs.core.view.RibView
import com.badoo.ribs.core.view.ViewFactory
import com.veriff.badooribs.domain.model.MockVerificationConfig
import io.reactivex.ObservableSource
import io.reactivex.functions.Consumer

interface StandbyView : RibView,
    ObservableSource<StandbyView.Event>,
    Consumer<StandbyView.ViewModel> {

    interface Factory : ViewFactory<StandbyView>

    sealed class ViewModel {
        object Loading : StandbyView.ViewModel()
        class Loaded(val configs: List<MockVerificationConfig>, val selectedIndex: Int) : StandbyView.ViewModel()
    }

    sealed class Event {
        class ScenarioSelected(val position: Int) : Event()
        object StartClicked : Event()
    }
}