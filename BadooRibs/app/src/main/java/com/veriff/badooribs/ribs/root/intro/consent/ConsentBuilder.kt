package com.veriff.badooribs.ribs.root.intro.consent

import com.badoo.ribs.builder.SimpleBuilder
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.core.view.ViewFactory
import com.veriff.badooribs.databinding.RibConsentBinding
import com.veriff.badooribs.util.inflater

class ConsentBuilder(
    private val deps: ConsentRib.Dependency
) : SimpleBuilder<ConsentRib>() {

    private class RibViewFactory : ConsentView.Factory {
        override fun invoke(context: ViewFactory.Context): ConsentView =
            ConsentViewImpl(RibConsentBinding.inflate(context.inflater()))
    }

    override fun build(buildParams: BuildParams<Nothing?>): ConsentRib =
        ConsentNode(
            buildParams = buildParams,
            viewFactory = RibViewFactory(),
            plugins = listOf(
                ConsentInteractor(buildParams)
            )
        )
}