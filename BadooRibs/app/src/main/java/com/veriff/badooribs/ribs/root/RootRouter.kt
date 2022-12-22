package com.veriff.badooribs.ribs.root

import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.routing.Routing
import com.badoo.ribs.routing.resolution.ChildResolution.Companion.child
import com.badoo.ribs.routing.resolution.Resolution
import com.badoo.ribs.routing.router.Router
import com.badoo.ribs.routing.source.RoutingSource
import com.badoo.ribs.routing.transition.handler.TransitionHandler
import com.veriff.badooribs.ribs.error.ErrorPayload
import com.veriff.badooribs.ribs.root.verification.VerificationFlowPayload

internal class RootRouter(
    buildParams: BuildParams<*>,
    routingSource: RoutingSource<RootRib.Configuration>,
    private val builders: RootChildBuilders,
    transitionHandler: TransitionHandler<RootRib.Configuration>? = null
) : Router<RootRib.Configuration>(
    buildParams = buildParams,
    routingSource = routingSource,
    transitionHandler = transitionHandler
) {

    private fun RootChildBuilders.attachError(payload: ErrorPayload) = child {
        errorBuilder.build(
            buildContext = it,
            payload = payload
        )
    }

    private fun RootChildBuilders.attachIntro() = child {
        introBuilder.build(
            buildContext = it,
        )
    }

    private fun RootChildBuilders.attachVerifyPhoto(payload: VerificationFlowPayload) = child {
        verificationFlowBuilder.build(
            buildContext = it,
            payload = payload
        )
    }

    override fun resolve(routing: Routing<RootRib.Configuration>): Resolution {
        return with(builders) {
            when (val configuration = routing.configuration) {
                is RootRib.Configuration.Error -> attachError(configuration.payload)
                is RootRib.Configuration.Intro -> attachIntro()
                is RootRib.Configuration.VerificationFlow -> attachVerifyPhoto(configuration.payload)
            }
        }
    }
}