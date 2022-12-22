package com.veriff.badooribs.ribs.root.verification.feedback

import androidx.lifecycle.Lifecycle
import com.badoo.binder.using
import com.badoo.mvicore.android.lifecycle.startStop
import com.badoo.ribs.clienthelper.interactor.Interactor
import com.badoo.ribs.core.modality.BuildParams
import com.veriff.badooribs.ribs.root.verification.feedback.InflowFeedbackRib.Output
import com.veriff.badooribs.ribs.root.verification.feedback.InflowFeedbackView.Event

internal class InflowFeedbackInteractor(
    private val buildParams: BuildParams<InflowFeedbackPayload>,
) : Interactor<InflowFeedbackRib, InflowFeedbackView>(buildParams) {
    override fun onViewCreated(view: InflowFeedbackView, viewLifecycle: Lifecycle) {
        viewLifecycle.startStop {
            // brief but not very readable
            bind(view to rib.output using ViewEventToOutput)
        }
    }


    internal object ViewEventToOutput : (Event) -> Output? {
        override fun invoke(event: Event): Output? =
            when (event) {
                is Event.ContinueClick -> Output.Continue
                is Event.RetryClick -> Output.Retry
            }
    }
}