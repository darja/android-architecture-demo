package com.veriff.badooribs.ribs.root.intro

import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.routing.Routing
import com.badoo.ribs.routing.resolution.ChildResolution.Companion.child
import com.badoo.ribs.routing.resolution.Resolution
import com.badoo.ribs.routing.router.Router
import com.badoo.ribs.routing.source.RoutingSource
import com.badoo.ribs.routing.transition.handler.TransitionHandler
import com.veriff.badooribs.ribs.root.intro.IntroRib.Configuration

internal class IntroRouter(
    buildParams: BuildParams<*>,
    routingSource: RoutingSource<Configuration>,
    private val builders: IntroChildBuilders,
    transitionHandler: TransitionHandler<Configuration>? = null
) : Router<Configuration>(
    buildParams = buildParams,
    routingSource = routingSource,
    transitionHandler = transitionHandler
) {

    override fun resolve(routing: Routing<Configuration>): Resolution {
        return with(builders) {
            when (routing.configuration) {
                is Configuration.Consent -> child { consentBuilder.build(it) }
                is Configuration.Standby -> child { standbyBuilder.build(it) }
            }
        }
    }
}