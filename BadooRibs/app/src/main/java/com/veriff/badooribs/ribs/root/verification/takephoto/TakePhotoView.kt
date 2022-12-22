package com.veriff.badooribs.ribs.root.verification.takephoto

import com.badoo.ribs.core.view.RibView
import com.badoo.ribs.core.view.ViewFactory
import io.reactivex.ObservableSource
import io.reactivex.functions.Consumer

interface TakePhotoView : RibView,
    ObservableSource<TakePhotoView.Event>,
    Consumer<TakePhotoView.ViewModel> {

    interface Factory : ViewFactory<TakePhotoView>

    sealed class Event {
        object CaptureClick : Event()
    }

    sealed class ViewModel {
        object ProcessingPhoto : ViewModel()

        class Document(val title: String, val subtitle: String, showFlip: Boolean) : ViewModel()

        class Selfie(val title: String, val subtitle: String) : ViewModel()

        class SelfieWithDocument(val title: String, val subtitle: String) : ViewModel()
    }
}