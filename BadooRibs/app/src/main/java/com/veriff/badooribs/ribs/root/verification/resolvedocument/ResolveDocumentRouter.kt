package com.veriff.badooribs.ribs.root.verification.resolvedocument

import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.routing.Routing
import com.badoo.ribs.routing.resolution.ChildResolution.Companion.child
import com.badoo.ribs.routing.resolution.Resolution
import com.badoo.ribs.routing.router.Router
import com.badoo.ribs.routing.source.RoutingSource
import com.badoo.ribs.routing.transition.handler.TransitionHandler
import com.veriff.badooribs.ribs.root.verification.resolvedocument.ResolveDocumentRib.Configuration.SelectCountry
import com.veriff.badooribs.ribs.root.verification.resolvedocument.ResolveDocumentRib.Configuration.SelectDocumentType

internal class ResolveDocumentRouter(
    buildParams: BuildParams<*>,
    routingSource: RoutingSource<ResolveDocumentRib.Configuration>,
    private val builders: ResolveDocumentChildBuilders,
    transitionHandler: TransitionHandler<ResolveDocumentRib.Configuration>? = null
) : Router<ResolveDocumentRib.Configuration>(
    buildParams = buildParams,
    routingSource = routingSource,
    transitionHandler = transitionHandler
) {

    override fun resolve(routing: Routing<ResolveDocumentRib.Configuration>): Resolution {
        with (builders) {
            return when (val configuration = routing.configuration) {
                is SelectCountry -> attachSelectCountry()
                is SelectDocumentType -> attachSelectDocumentType()
                else -> Resolution.noop()
            }
        }
    }

    private fun ResolveDocumentChildBuilders.attachSelectCountry() = child {
        selectCountryBuilder.build(it)
    }

    private fun ResolveDocumentChildBuilders.attachSelectDocumentType() = child {
        selectDocumentTypeBuilder.build(it)
    }
}