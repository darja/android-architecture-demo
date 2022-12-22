package com.veriff.badooribs.ribs.root.verification.feedback

import com.badoo.ribs.core.view.RibView
import com.badoo.ribs.core.view.ViewFactory
import io.reactivex.ObservableSource

interface InflowFeedbackView : RibView,
    ObservableSource<InflowFeedbackView.Event> {

    interface Factory : ViewFactory<InflowFeedbackView>

    sealed class Event {
        object RetryClick : Event()
        object ContinueClick : Event()
    }
}