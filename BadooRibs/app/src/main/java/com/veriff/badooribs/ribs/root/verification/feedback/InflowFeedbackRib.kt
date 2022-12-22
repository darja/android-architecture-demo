package com.veriff.badooribs.ribs.root.verification.feedback

import com.badoo.ribs.core.Rib
import com.badoo.ribs.rx2.clienthelper.connector.Connectable

interface InflowFeedbackRib : Rib, Connectable<InflowFeedbackRib.Input, InflowFeedbackRib.Output> {
    interface Dependency

    sealed class Input

    sealed class Output {
        object Retry : Output()
        object Continue : Output()
    }
}