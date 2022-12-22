package com.veriff.badooribs.ribs.root.intro

import com.badoo.ribs.builder.SimpleBuilder
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.routing.source.backstack.BackStack
import com.veriff.badooribs.ribs.root.intro.IntroRib.Configuration
import com.veriff.badooribs.ribs.root.intro.IntroRib.Customisation

class IntroBuilder(
    deps: IntroRib.Dependency,
) : SimpleBuilder<IntroRib>() {

    private val builders = IntroChildBuilders(deps)

    override fun build(buildParams: BuildParams<Nothing?>): IntroRib {
        val customisation = buildParams.getOrDefault(Customisation())
        val backStack = BackStack<Configuration>(
            buildParams = buildParams,
            initialConfiguration = Configuration.Consent // todo logic here
        )

        val interactor = IntroInteractor(
            buildParams = buildParams,
            backStack = backStack
        )

        val router = IntroRouter(
            buildParams = buildParams,
            builders = builders,
            routingSource = backStack,
            transitionHandler = customisation.transitionHandler
        )

        return IntroNode(
            buildParams = buildParams,
            plugins = listOf(interactor, router)
        )
    }
}