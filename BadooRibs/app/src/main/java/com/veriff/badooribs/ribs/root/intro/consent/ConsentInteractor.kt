package com.veriff.badooribs.ribs.root.intro.consent

import androidx.lifecycle.Lifecycle
import com.badoo.binder.using
import com.badoo.mvicore.android.lifecycle.startStop
import com.badoo.ribs.clienthelper.interactor.Interactor
import com.badoo.ribs.core.modality.BuildParams

internal class ConsentInteractor(
    private val buildParams: BuildParams<*>
): Interactor<ConsentRib, ConsentView>(buildParams) {
    override fun onViewCreated(view: ConsentView, viewLifecycle: Lifecycle) {
        viewLifecycle.startStop {
            // brief but not very readable
            bind(view to rib.output using ViewEventToOutput)
        }
    }

    private object ViewEventToOutput : (ConsentView.Event) -> ConsentRib.Output? {
        override fun invoke(event: ConsentView.Event): ConsentRib.Output? =
            when (event) {
                is ConsentView.Event.AcceptClicked -> ConsentRib.Output.Accepted
                is ConsentView.Event.DeclineClicked -> ConsentRib.Output.Declined
            }
    }

}