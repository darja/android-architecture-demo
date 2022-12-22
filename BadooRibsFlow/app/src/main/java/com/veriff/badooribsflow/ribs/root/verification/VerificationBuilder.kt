package com.veriff.badooribsflow.ribs.root.verification

import com.badoo.ribs.builder.SimpleBuilder
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.core.view.ViewFactory
import com.veriff.badooribsflow.databinding.RibVerificationBinding
import com.veriff.badooribsflow.util.inflater

class VerificationBuilder(
    private val deps: VerificationRib.Dependency,
) : SimpleBuilder<VerificationRib>() {
    private class VerificationViewFactory : VerificationView.Factory {
        override fun invoke(context: ViewFactory.Context): VerificationView =
            VerificationViewImpl(RibVerificationBinding.inflate(context.inflater()))
    }

    override fun build(buildParams: BuildParams<Nothing?>): VerificationRib =
        VerificationNode(
            buildParams = buildParams,
            viewFactory = VerificationViewFactory(),
            plugins = listOf(
                VerificationInteractor(buildParams)
            )
        )

}
