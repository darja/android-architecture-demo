package com.veriff.badooribsflow.ribs.root

import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.routing.Routing
import com.badoo.ribs.routing.resolution.ChildResolution.Companion.child
import com.badoo.ribs.routing.resolution.Resolution
import com.badoo.ribs.routing.router.Router
import com.badoo.ribs.routing.source.RoutingSource
import com.badoo.ribs.routing.transition.handler.TransitionHandler

internal class RootRouter(
    buildParams: BuildParams<*>,
    routingSource: RoutingSource<RootRib.RoutingState>,
    private val builders: RootChildBuilders,
    transitionHandler: TransitionHandler<RootRib.RoutingState>? = null
) : Router<RootRib.RoutingState>(
    buildParams = buildParams,
    routingSource = routingSource,
    transitionHandler = transitionHandler
) {
    private fun RootChildBuilders.attachIntro() = child {
        introBuilder.build(
            buildContext = it,
        )
    }

    private fun RootChildBuilders.attachVerification() = child {
        verificationBuilder.build(
            buildContext = it,
        )
    }

    override fun resolve(routing: Routing<RootRib.RoutingState>): Resolution {
        return with(builders) {
            when (routing.state) {
                is RootRib.RoutingState.Intro -> attachIntro()
                is RootRib.RoutingState.Verification -> attachVerification()
            }
        }
    }
}
