package com.veriff.badooribsflow.ribs.root.intro

import com.badoo.ribs.core.view.RibView
import com.badoo.ribs.core.view.ViewFactory
import com.veriff.badooribsflow.ribs.root.intro.IntroView.Event
import kotlinx.coroutines.flow.Flow

interface IntroView: RibView, Flow<Event> {
    interface Factory : ViewFactory<IntroView>

    sealed class Event {
        object ContinueClicked : Event()
        object CancelClicked : Event()
    }
}
