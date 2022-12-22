package com.veriff.badooribs.ribs.root.verification.resolvedocument.selectcountry

import com.badoo.ribs.builder.SimpleBuilder
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.core.view.ViewFactory

class SelectCountryBuilder(
    private val deps: SelectCountryRib.Dependency
) : SimpleBuilder<SelectCountryRib>() {

    private class RibViewFactory : SelectCountryView.Factory {
        override fun invoke(context: ViewFactory.Context): SelectCountryView =
            SelectCountryViewImpl(context)
    }

    override fun build(buildParams: BuildParams<Nothing?>): SelectCountryRib {
        val interactor = SelectCountryInteractor(
            buildParams = buildParams,
        )

        return SelectCountryNode(
            buildParams = buildParams,
            viewFactory = RibViewFactory(),
            plugins = listOf(
                interactor
            )
        )
    }
}