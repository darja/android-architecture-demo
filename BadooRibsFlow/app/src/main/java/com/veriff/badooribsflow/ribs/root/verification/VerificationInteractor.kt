package com.veriff.badooribsflow.ribs.root.verification

import androidx.lifecycle.Lifecycle
import com.badoo.ribs.clienthelper.interactor.Interactor
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.flow.lifecycle.collect
import com.badoo.ribs.flow.lifecycle.using
import com.badoo.ribs.flow.lifecycle.whileAlive

class VerificationInteractor(
    buildParams: BuildParams<*>,
) : Interactor<VerificationRib, VerificationView>(buildParams) {
    override fun onViewCreated(view: VerificationView, viewLifecycle: Lifecycle) {
        viewLifecycle.whileAlive {
            collect(view to rib.output using eventToOutput)
        }
    }

    private val eventToOutput: (VerificationView.Event) -> VerificationRib.Output = {
        when (it) {
            is VerificationView.Event.VerifyClicked -> VerificationRib.Output.Finished
        }
    }
}
