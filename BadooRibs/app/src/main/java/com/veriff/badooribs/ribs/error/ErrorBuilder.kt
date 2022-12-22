package com.veriff.badooribs.ribs.error

import com.badoo.ribs.builder.Builder
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.core.view.ViewFactory
import com.veriff.badooribs.databinding.RibErrorBinding
import com.veriff.badooribs.util.inflater

class ErrorBuilder(
    private val deps: ErrorRib.Dependency
) : Builder<ErrorPayload, ErrorRib>() {

    class RibViewFactory : ErrorView.Factory {
        override fun invoke(context: ViewFactory.Context): ErrorView =
            ErrorViewImpl(RibErrorBinding.inflate(context.inflater()))
    }

    override fun build(buildParams: BuildParams<ErrorPayload>): ErrorRib =
        ErrorNode(
            buildParams = buildParams,
            viewFactory = RibViewFactory(),
            plugins = listOf(
                ErrorInteractor(buildParams)
            )
        )
}