package com.veriff.badooribs.ribs.root.verification.takephoto

import com.badoo.ribs.builder.Builder
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.core.view.ViewFactory

class TakePhotoBuilder(
    private val deps: TakePhotoRib.Dependency
) : Builder<TakePhotoPayload, TakePhotoRib>() {

    private class RibViewFactory : TakePhotoView.Factory {
        override fun invoke(context: ViewFactory.Context): TakePhotoView =
            TakePhotoViewImpl(context)
    }

    override fun build(buildParams: BuildParams<TakePhotoPayload>): TakePhotoRib {
        val feature = TakePhotoFeature(buildParams.payload.target)

        val interactor = TakePhotoInteractor(
            buildParams = buildParams,
            feature = feature
        )

        return TakePhotoNode(
            buildParams = buildParams,
            viewFactory = RibViewFactory(),
            plugins = listOf(
                interactor
            )
        )
    }
}