package com.veriff.badooribs.ribs.root.intro.standby

import com.badoo.ribs.builder.SimpleBuilder
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.core.view.ViewFactory
import com.veriff.badooribs.databinding.RibStandbyBinding
import com.veriff.badooribs.util.inflater

class StandbyBuilder(
    private val deps: StandbyRib.Dependency
) : SimpleBuilder<StandbyRib>() {

    private class RibViewFactory : StandbyView.Factory {
        override fun invoke(context: ViewFactory.Context): StandbyView =
            StandbyViewImpl(RibStandbyBinding.inflate(context.inflater()))
    }

    override fun build(buildParams: BuildParams<Nothing?>): StandbyRib {
        val feature = StandbyFeature(deps)
        val interactor = StandbyInteractor(feature, buildParams)

        return StandbyNode(
            buildParams = buildParams,
            viewFactory = RibViewFactory(),
            plugins = listOf(interactor)
        )
    }
}