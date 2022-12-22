package com.veriff.badooribs.ribs.root.intro

import androidx.lifecycle.Lifecycle
import com.badoo.binder.using
import com.badoo.mvicore.android.lifecycle.createDestroy
import com.badoo.ribs.clienthelper.childaware.whenChildBuilt
import com.badoo.ribs.clienthelper.interactor.Interactor
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.routing.source.backstack.BackStack
import com.badoo.ribs.routing.source.backstack.operation.replace
import com.veriff.badooribs.ribs.root.intro.consent.ConsentRib
import com.veriff.badooribs.ribs.root.intro.standby.StandbyRib
import io.reactivex.functions.Consumer

internal class IntroInteractor(
    private val backStack: BackStack<IntroRib.Configuration>,
    buildParams: BuildParams<*>,
) : Interactor<IntroRib, Nothing>(
    buildParams = buildParams
) {
    override fun onCreate(nodeLifecycle: Lifecycle) {
        nodeLifecycle.createDestroy {}

        whenChildBuilt<ConsentRib>(nodeLifecycle) { commonLifecycle, child ->
            commonLifecycle.createDestroy {
                bind(child.output to consentListener)
                bind(child.output to rib.output using ConsentToIntroOutput)
            }
        }

        whenChildBuilt<StandbyRib>(nodeLifecycle) { commonLifecycle, child ->
            commonLifecycle.createDestroy {
                bind(child.output to rib.output using StandbyToIntroOutput)
            }
        }
    }

    private val consentListener = Consumer<ConsentRib.Output> { output ->
        when (output) {
            ConsentRib.Output.Accepted -> backStack.replace(IntroRib.Configuration.Standby)
            else -> null
        }
    }

    private object ConsentToIntroOutput : (ConsentRib.Output) -> IntroRib.Output? {
        override fun invoke(from: ConsentRib.Output): IntroRib.Output? {
            return when (from) {
                is ConsentRib.Output.Declined -> IntroRib.Output.Cancelled
                else -> null
            }
        }
    }

    private object StandbyToIntroOutput : (StandbyRib.Output) -> IntroRib.Output? {
        override fun invoke(from: StandbyRib.Output): IntroRib.Output? {
            return when (from) {
                is StandbyRib.Output.VerificationStart -> IntroRib.Output.Finished(from.scenario)
            }
        }
    }
}