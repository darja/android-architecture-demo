package com.veriff.badooribs.ribs.root

import android.os.Parcelable
import com.badoo.ribs.core.Rib
import com.badoo.ribs.core.customisation.RibCustomisation
import com.badoo.ribs.routing.transition.handler.TransitionHandler
import com.veriff.badooribs.ribs.RibDependency
import com.veriff.badooribs.ribs.error.ErrorPayload
import com.veriff.badooribs.ribs.root.verification.VerificationFlowPayload
import kotlinx.parcelize.Parcelize

interface RootRib : Rib {
    interface Dependency : RibDependency

    sealed class Configuration : Parcelable {
        @Parcelize
        class Error(val payload: ErrorPayload) : Configuration()

        @Parcelize
        object Intro : Configuration()

        @Parcelize
        class VerificationFlow(val payload: VerificationFlowPayload) : Configuration()
    }

    class Customisation(
        val transitionHandler: TransitionHandler<Configuration>? = null
    ) : RibCustomisation
}