package com.veriff.badooribsflow.ribs.root

import com.badoo.ribs.builder.SimpleBuilder
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.routing.source.backstack.BackStack

class RootBuilder(
    private val deps: RootRib.Dependency,
) : SimpleBuilder<RootRib>() {

    private val builders = RootChildBuilders(deps)

    override fun build(buildParams: BuildParams<Nothing?>): RootRib {
        val customisation = buildParams.getOrDefault(RootRib.RoutingConfig())
        val backStack = BackStack<RootRib.RoutingState>(
            buildParams = buildParams,
            initialConfiguration = RootRib.RoutingState.Intro
        )

        val interactor = RootInteractor(
            backStack = backStack,
            buildParams = buildParams,
        )

        val router = RootRouter(
            buildParams = buildParams,
            builders = builders,
            routingSource = backStack,
            transitionHandler = customisation.transitionHandler
        )

        return RootNode(
            buildParams = buildParams,
            plugins = listOf(interactor, router)
        )
    }
}
