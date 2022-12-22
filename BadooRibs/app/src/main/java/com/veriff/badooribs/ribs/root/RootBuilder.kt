package com.veriff.badooribs.ribs.root

import com.badoo.ribs.builder.SimpleBuilder
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.routing.source.backstack.BackStack
import com.veriff.badooribs.ribs.root.RootRib.Configuration

class RootBuilder(
    deps: RootRib.Dependency,
) : SimpleBuilder<RootRib>() {

    private val builders = RootChildBuilders(deps)

    override fun build(buildParams: BuildParams<Nothing?>): RootRib {
        val customisation = buildParams.getOrDefault(RootRib.Customisation())
        val backStack = BackStack<Configuration>(
            buildParams = buildParams,
            initialConfiguration = Configuration.Intro
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