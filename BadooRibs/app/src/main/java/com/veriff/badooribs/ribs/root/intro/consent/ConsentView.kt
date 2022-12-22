package com.veriff.badooribs.ribs.root.intro.consent

import com.badoo.ribs.core.view.RibView
import com.badoo.ribs.core.view.ViewFactory
import io.reactivex.ObservableSource

interface ConsentView : RibView,
    ObservableSource<ConsentView.Event> {

    interface Factory : ViewFactory<ConsentView>

    sealed class Event {
        object AcceptClicked : Event()
        object DeclineClicked : Event()
    }
}