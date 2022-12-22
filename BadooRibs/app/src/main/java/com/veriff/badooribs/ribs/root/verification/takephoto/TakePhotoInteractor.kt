package com.veriff.badooribs.ribs.root.verification.takephoto

import androidx.lifecycle.Lifecycle
import com.badoo.binder.using
import com.badoo.mvicore.android.lifecycle.startStop
import com.badoo.ribs.clienthelper.interactor.Interactor
import com.badoo.ribs.core.modality.BuildParams
import com.veriff.badooribs.ribs.root.verification.takephoto.TakePhotoFeature.State
import com.veriff.badooribs.ribs.root.verification.takephoto.TakePhotoFeature.Wish
import com.veriff.badooribs.ribs.root.verification.takephoto.TakePhotoRib.Output
import com.veriff.badooribs.ribs.root.verification.takephoto.TakePhotoView.Event
import com.veriff.badooribs.ribs.root.verification.takephoto.mappers.FeatureToViewModel
import com.veriff.badooribs.util.LogConsumer

internal class TakePhotoInteractor(
    private val buildParams: BuildParams<TakePhotoPayload>,
    private val feature: TakePhotoFeature,
) : Interactor<TakePhotoRib, TakePhotoView>(buildParams) {
    override fun onViewCreated(view: TakePhotoView, viewLifecycle: Lifecycle) {
        viewLifecycle.startStop {
            bind(rib.input to feature using InputToFeature)
            bind(feature to view using FeatureToViewModel())
            bind(feature to rib.output using StateToOutput)
            bind(view to feature using ViewEventToWish)

            bind(rib.input to LogConsumer("TakePhoto", "Input event"))
            bind(feature to LogConsumer("TakePhoto", "Feature state"))
        }
    }

    private object StateToOutput : (State) -> Output? {
        override fun invoke(state: State): Output? =
            when (state) {
                is State.PhotoTaken -> Output.ArtifactProduced(state.artifact)
                else -> null
            }
    }

    private object ViewEventToWish : (Event) -> Wish? {
        override fun invoke(event: Event): Wish =
            when (event) {
                is Event.CaptureClick -> Wish.TakePhoto
            }
    }

    private object InputToFeature : (TakePhotoRib.Input) -> Wish? {
        override fun invoke(event: TakePhotoRib.Input): Wish =
            when (event) {
                is TakePhotoRib.Input.ChangeTarget -> Wish.ChangeTarget(event.target)
            }
    }
}