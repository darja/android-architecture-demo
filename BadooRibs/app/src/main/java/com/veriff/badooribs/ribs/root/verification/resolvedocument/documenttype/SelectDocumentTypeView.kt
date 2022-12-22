package com.veriff.badooribs.ribs.root.verification.resolvedocument.documenttype

import com.badoo.ribs.core.view.RibView
import com.badoo.ribs.core.view.ViewFactory
import io.reactivex.ObservableSource

interface SelectDocumentTypeView : RibView,
    ObservableSource<SelectDocumentTypeView.Event> {

    interface Factory : ViewFactory<SelectDocumentTypeView>

    sealed class Event {
        object IdCardSelected : Event()
        object PassportSelected : Event()
        object DrivingLicenseSelected : Event()
        object ResidencePermitSelected : Event()
    }
}