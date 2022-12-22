package com.veriff.badooribsflow.ribs.root

import android.util.Log
import androidx.lifecycle.Lifecycle
import com.badoo.ribs.clienthelper.childaware.whenChildBuilt
import com.badoo.ribs.clienthelper.interactor.Interactor
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.flow.lifecycle.collect
import com.badoo.ribs.flow.lifecycle.whileAlive
import com.badoo.ribs.routing.source.backstack.BackStack
import com.badoo.ribs.routing.source.backstack.operation.replace
import com.veriff.badooribsflow.ribs.root.intro.IntroRib
import com.veriff.badooribsflow.ribs.root.verification.VerificationRib
import com.veriff.badooribsflow.util.classSimpleName
import kotlinx.coroutines.flow.FlowCollector

class RootInteractor(
    private val backStack: BackStack<RootRib.RoutingState>,
    buildParams: BuildParams<*>,
) : Interactor<RootRib, Nothing>(
    buildParams = buildParams
) {
    override fun onCreate(nodeLifecycle: Lifecycle) {
        whenChildBuilt<IntroRib>(nodeLifecycle) { commonLifecycle, child ->
            commonLifecycle.whileAlive {
                collect(child.output to IntroListener())
            }
        }

        whenChildBuilt<VerificationRib>(nodeLifecycle) { commonLifecycle, child ->
            commonLifecycle.whileAlive {
                collect(child.output to VerificationListener())
            }
        }

        Log.d("--dev", "RootInteractor is created")
    }

    override fun onDestroy() {
        Log.d("--dev", "RootInteractor is destroyed")
    }

    private inner class IntroListener : FlowCollector<IntroRib.Output> {
        override suspend fun emit(value: IntroRib.Output) {
            Log.d("--dev", "Root got an event from Intro: [${value.classSimpleName}]")

            when (value) {
                is IntroRib.Output.Finished -> backStack.replace(RootRib.RoutingState.Verification)
                is IntroRib.Output.Cancelled -> Unit
            }
        }
    }

    private inner class VerificationListener : FlowCollector<VerificationRib.Output> {
        override suspend fun emit(value: VerificationRib.Output) {
            Log.d("--dev", "Root got an event from Verification: [${value.classSimpleName}]")
            when (value) {
                is VerificationRib.Output.Finished -> backStack.replace(RootRib.RoutingState.Intro)
            }
        }
    }
}
