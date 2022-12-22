package com.veriff.badooribsflow.ribs.root.verification

import com.badoo.ribs.core.view.RibView
import com.badoo.ribs.core.view.ViewFactory
import kotlinx.coroutines.flow.Flow

interface VerificationView : RibView, Flow<VerificationView.Event> {
    interface Factory : ViewFactory<VerificationView>

    sealed class Event {
        object VerifyClicked: Event()
    }
}
