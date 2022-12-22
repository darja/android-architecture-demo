package com.veriff.badooribsflow.ribs.root

import android.os.Parcelable
import com.badoo.ribs.core.Rib
import com.badoo.ribs.core.customisation.RibRoutingConfig
import com.badoo.ribs.routing.transition.handler.CrossFader
import com.badoo.ribs.routing.transition.handler.Slider
import com.badoo.ribs.routing.transition.handler.TransitionHandler
import com.veriff.badooribsflow.ribs.RibDependency
import kotlinx.parcelize.Parcelize

interface RootRib: Rib {
    interface Dependency : RibDependency

    sealed class RoutingState : Parcelable {
        @Parcelize
        object Intro : RoutingState()

        @Parcelize
        object Verification : RoutingState()
    }

    class RoutingConfig(
        val transitionHandler: TransitionHandler<RoutingState>? = TransitionHandler.multiple(
            Slider { it.configuration is RoutingState.Intro },
            CrossFader { it.configuration is RoutingState.Verification }
        )
    ) : RibRoutingConfig
}
