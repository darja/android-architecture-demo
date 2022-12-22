package com.veriff.badooribs.ribs.root.ResolveDocument.resolvedocument

import com.badoo.ribs.builder.SimpleBuilder
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.routing.source.backstack.BackStack
import com.veriff.badooribs.ribs.root.verification.resolvedocument.*

class ResolveDocumentBuilder(
    private val deps: ResolveDocumentRib.Dependency,
) : SimpleBuilder<ResolveDocumentRib>() {

    private val builders = ResolveDocumentChildBuilders(deps)

    override fun build(buildParams: BuildParams<Nothing?>): ResolveDocumentRib {
        val customisation = buildParams.getOrDefault(ResolveDocumentRib.Customisation())
        val backStack = BackStack<ResolveDocumentRib.Configuration>(
            buildParams = buildParams,
            initialConfiguration = ResolveDocumentRib.Configuration.SelectCountry
        )

        val interactor = ResolveDocumentInteractor(
            deps = deps,
            buildParams = buildParams,
            backStack = backStack
        )

        val router = ResolveDocumentRouter(
            buildParams = buildParams,
            builders = builders,
            routingSource = backStack,
            transitionHandler = customisation.transitionHandler
        )

        return ResolveDocumentNode(
            buildParams = buildParams,
            plugins = listOf(interactor, router)
        )
    }
}