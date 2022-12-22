package com.veriff.badooribs.ribs.root.verification.feedback

import com.badoo.ribs.builder.Builder
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.core.view.ViewFactory

class InflowFeedbackBuilder(
    private val deps: InflowFeedbackRib.Dependency
) : Builder<InflowFeedbackPayload, InflowFeedbackRib>() {

    private class RibViewFactory : InflowFeedbackView.Factory {
        override fun invoke(context: ViewFactory.Context): InflowFeedbackView =
            InflowFeedbackViewImpl(context)
    }

    override fun build(buildParams: BuildParams<InflowFeedbackPayload>): InflowFeedbackRib {
        val interactor = InflowFeedbackInteractor(
            buildParams = buildParams,
        )

        return InflowFeedbackNode(
            buildParams = buildParams,
            viewFactory = RibViewFactory(),
            plugins = listOf(
                interactor
            )
        )
    }

}