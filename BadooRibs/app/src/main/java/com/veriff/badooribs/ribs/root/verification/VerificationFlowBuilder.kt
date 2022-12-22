package com.veriff.badooribs.ribs.root.verification

import com.badoo.ribs.builder.Builder
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.routing.source.backstack.BackStack
import com.veriff.badooribs.ribs.root.verification.VerificationFlowRib.Configuration
import com.veriff.badooribs.ribs.root.verification.VerificationFlowRib.Customisation

class VerificationFlowBuilder(
    private val deps: VerificationFlowRib.Dependency,
) : Builder<VerificationFlowPayload, VerificationFlowRib>() {

    private val builders = VerificationFlowChildBuilders(deps)

    override fun build(buildParams: BuildParams<VerificationFlowPayload>): VerificationFlowRib {
        val customisation = buildParams.getOrDefault(Customisation())
        val backStack = BackStack<Configuration>(
            buildParams = buildParams,
            initialConfiguration = Configuration.CheckPermission
        )

        val feature = VerificationFlowFeature(
            deps = deps,
            scenario = buildParams.payload.scenario
        )

        val interactor = VerificationFlowInteractor(
            buildParams = buildParams,
            backStack = backStack,
            feature = feature
        )

        val router = VerificationFlowRouter(
            buildParams = buildParams,
            builders = builders,
            routingSource = backStack,
            transitionHandler = customisation.transitionHandler
        )

        return VerificationFlowNode(
            buildParams = buildParams,
            plugins = listOf(interactor, router)
        )
    }
}