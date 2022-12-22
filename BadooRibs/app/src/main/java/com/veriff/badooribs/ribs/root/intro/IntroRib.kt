package com.veriff.badooribs.ribs.root.intro

import android.os.Parcelable
import com.badoo.ribs.core.Rib
import com.badoo.ribs.core.customisation.RibCustomisation
import com.badoo.ribs.routing.transition.handler.TransitionHandler
import com.badoo.ribs.rx2.clienthelper.connector.Connectable
import com.veriff.badooribs.domain.model.VerificationScenario
import com.veriff.badooribs.ribs.RibDependency
import kotlinx.parcelize.Parcelize

interface IntroRib : Rib, Connectable<IntroRib.Input, IntroRib.Output> {
    interface Dependency : RibDependency

    sealed class Configuration : Parcelable {
        @Parcelize
        object Standby : Configuration()

        @Parcelize
        object Consent : Configuration()
    }

    sealed class Input

    sealed class Output {
        class Finished(val scenario: VerificationScenario) : Output()
        object Cancelled : Output()
    }

    class Customisation(
        val transitionHandler: TransitionHandler<Configuration>? = null,
    ) : RibCustomisation
}