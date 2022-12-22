package com.veriff.badooribs.ribs.root.verification.takephoto

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.badoo.ribs.core.view.AndroidRibView
import com.badoo.ribs.core.view.ViewFactory
import com.jakewharton.rxrelay2.PublishRelay
import com.veriff.badooribs.databinding.RibTakePhotoBinding
import com.veriff.badooribs.ribs.root.verification.takephoto.TakePhotoView.ViewModel
import com.veriff.badooribs.util.inflater
import io.reactivex.ObservableSource

internal class TakePhotoViewImpl(
    context: ViewFactory.Context,
    private val events: PublishRelay<TakePhotoView.Event> = PublishRelay.create()
) :
    AndroidRibView(),
    TakePhotoView,
    ObservableSource<TakePhotoView.Event> by events {

    private val binding = RibTakePhotoBinding.inflate(context.inflater())

    override val androidView: ViewGroup
        get() = binding.root

    init {
        binding.captureButton.setOnClickListener {
            events.accept(TakePhotoView.Event.CaptureClick)
        }
    }

    override fun accept(viewModel: ViewModel) {
        binding.progress.isVisible = viewModel is ViewModel.ProcessingPhoto
        binding.captureButton.isInvisible = viewModel is ViewModel.ProcessingPhoto

        when (viewModel) {
            is ViewModel.ProcessingPhoto -> showProcessingPhoto()
            is ViewModel.Document -> showDocumentPreview(viewModel)
            is ViewModel.Selfie -> showSelfiePreview(viewModel)
            is ViewModel.SelfieWithDocument -> showSelfieWithDocumentPreview(viewModel)
        }
    }

    private fun showProcessingPhoto() {
        with (binding) {
            val fadeIn = ObjectAnimator
                .ofFloat(highlight, "alpha", 0f, 1f)
                .setDuration(200)
            val fadeOut = ObjectAnimator
                .ofFloat(highlight, "alpha", 1f, 0f)
                .setDuration(200)

            AnimatorSet().apply {
                playSequentially(fadeIn, fadeOut)
                start()
            }
        }
    }

    private fun showDocumentPreview(viewModel: ViewModel.Document) {
        with (binding) {
            documentPreview.isVisible = true
            selfiePreview.isGone = true
            selfieDocumentPreview.isGone = true

            title.text = viewModel.title
            subtitle.text = viewModel.subtitle
        }
    }

    private fun showSelfiePreview(viewModel: ViewModel.Selfie) {
        with (binding) {
            documentPreview.isGone = true
            selfiePreview.isVisible = true
            selfieDocumentPreview.isGone = true

            title.text = viewModel.title
            subtitle.text = viewModel.subtitle
        }
    }

    private fun showSelfieWithDocumentPreview(viewModel: ViewModel.SelfieWithDocument) {
        with (binding) {
            documentPreview.isGone = true
            selfiePreview.isVisible = true
            selfieDocumentPreview.isVisible = true

            title.text = viewModel.title
            subtitle.text = viewModel.subtitle
        }
    }
}