package com.veriff.badooribs.ribs.root.verification.resolvedocument.SelectDocumentType

import com.badoo.ribs.builder.SimpleBuilder
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.core.view.ViewFactory
import com.veriff.badooribs.ribs.root.verification.resolvedocument.documenttype.*

class SelectDocumentTypeBuilder(
    private val deps: SelectDocumentTypeRib.Dependency
) : SimpleBuilder<SelectDocumentTypeRib>() {

    private class RibViewFactory : SelectDocumentTypeView.Factory {
        override fun invoke(context: ViewFactory.Context): SelectDocumentTypeView =
            SelectDocumentTypeViewImpl(context)
    }

    override fun build(buildParams: BuildParams<Nothing?>): SelectDocumentTypeRib {
        val interactor = SelectDocumentTypeInteractor(
            buildParams = buildParams,
        )

        return SelectDocumentTypeNode(
            buildParams = buildParams,
            viewFactory = RibViewFactory(),
            plugins = listOf(
                interactor
            )
        )
    }
}