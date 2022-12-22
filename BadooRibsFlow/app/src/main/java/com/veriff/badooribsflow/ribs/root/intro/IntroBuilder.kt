package com.veriff.badooribsflow.ribs.root.intro

import com.badoo.ribs.builder.SimpleBuilder
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.core.view.ViewFactory
import com.veriff.badooribsflow.databinding.RibIntroBinding
import com.veriff.badooribsflow.util.inflater

class IntroBuilder(
    private val deps: IntroRib.Dependency,
) : SimpleBuilder<IntroRib>() {

    private class IntroViewFactory : IntroView.Factory {
        override fun invoke(context: ViewFactory.Context): IntroView =
            IntroViewImpl(RibIntroBinding.inflate(context.inflater()))
    }

    override fun build(buildParams: BuildParams<Nothing?>): IntroRib =
        IntroNode(
            buildParams = buildParams,
            viewFactory = IntroViewFactory(),
            plugins = listOf(
                IntroInteractor(buildParams)
            )
        )
}
