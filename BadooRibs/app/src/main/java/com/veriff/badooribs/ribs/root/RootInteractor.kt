package com.veriff.badooribs.ribs.root

import androidx.lifecycle.Lifecycle
import com.badoo.mvicore.android.lifecycle.createDestroy
import com.badoo.ribs.clienthelper.childaware.whenChildBuilt
import com.badoo.ribs.clienthelper.interactor.Interactor
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.routing.source.backstack.BackStack
import com.badoo.ribs.routing.source.backstack.operation.replace
import com.veriff.badooribs.ribs.error.ErrorPayload
import com.veriff.badooribs.ribs.root.intro.IntroRib
import com.veriff.badooribs.ribs.root.verification.VerificationFlowPayload
import com.veriff.badooribs.ribs.root.verification.VerificationFlowRib
import io.reactivex.functions.Consumer

class RootInteractor(
    private val backStack: BackStack<RootRib.Configuration>,
    buildParams: BuildParams<*>,
) : Interactor<RootRib, Nothing>(
    buildParams = buildParams
) {
    override fun onCreate(nodeLifecycle: Lifecycle) {
        nodeLifecycle.createDestroy {}

        whenChildBuilt<IntroRib>(nodeLifecycle) { commonLifecycle, child ->
            commonLifecycle.createDestroy {
                bind(child.output to introListener)
            }
        }

        whenChildBuilt<VerificationFlowRib>(nodeLifecycle) { commonLifecycle, child ->
            commonLifecycle.createDestroy {
                bind(child.output to verificationFlowListener)
            }
        }
    }

    private val introListener = Consumer<IntroRib.Output> { event ->
        when (event) {
            is IntroRib.Output.Finished ->
                backStack.replace(RootRib.Configuration.VerificationFlow(VerificationFlowPayload(event.scenario)))
            is IntroRib.Output.Cancelled ->
                backStack.replace(RootRib.Configuration.Error(ErrorPayload.VerificationCancelled))
        }
    }

    private val verificationFlowListener = Consumer<VerificationFlowRib.Output> { event ->
        when (event) {
            is VerificationFlowRib.Output.Success ->
                backStack.replace(RootRib.Configuration.Intro)
            is VerificationFlowRib.Output.Fail ->
                backStack.replace(RootRib.Configuration.Error(ErrorPayload.VerificationError))
        }
    }
}