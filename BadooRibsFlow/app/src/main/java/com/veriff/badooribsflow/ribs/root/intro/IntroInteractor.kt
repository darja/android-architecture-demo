package com.veriff.badooribsflow.ribs.root.intro

import android.util.Log
import androidx.lifecycle.Lifecycle
import com.badoo.ribs.clienthelper.interactor.Interactor
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.flow.lifecycle.collect
import com.badoo.ribs.flow.lifecycle.using
import com.badoo.ribs.flow.lifecycle.whileAlive

internal class IntroInteractor(
    buildParams: BuildParams<*>,
) : Interactor<IntroRib, IntroView>(buildParams) {

    override fun onViewCreated(view: IntroView, viewLifecycle: Lifecycle) {
        viewLifecycle.whileAlive {
            collect(view to rib.output using eventToOutput)
        }
    }

    override fun onCreate(nodeLifecycle: Lifecycle) {
        super.onCreate(nodeLifecycle)
        Log.d("--dev", "Intro created")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("--dev", "Intro destroyed")
    }

    private val eventToOutput: (IntroView.Event) -> IntroRib.Output = {
        when (it) {
            is IntroView.Event.ContinueClicked -> IntroRib.Output.Finished
            is IntroView.Event.CancelClicked -> IntroRib.Output.Cancelled
        }
    }
}
