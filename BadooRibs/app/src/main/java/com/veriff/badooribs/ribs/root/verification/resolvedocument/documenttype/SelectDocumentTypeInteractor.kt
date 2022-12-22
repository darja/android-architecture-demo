package com.veriff.badooribs.ribs.root.verification.resolvedocument.documenttype

import androidx.lifecycle.Lifecycle
import com.badoo.binder.using
import com.badoo.mvicore.android.lifecycle.startStop
import com.badoo.ribs.clienthelper.interactor.Interactor
import com.badoo.ribs.core.modality.BuildParams
import com.veriff.badooribs.domain.model.DocumentType
import com.veriff.badooribs.ribs.root.verification.resolvedocument.documenttype.SelectDocumentTypeRib.Output
import com.veriff.badooribs.ribs.root.verification.resolvedocument.documenttype.SelectDocumentTypeView.Event.*

internal class SelectDocumentTypeInteractor(
    private val buildParams: BuildParams<*>,
) : Interactor<SelectDocumentTypeRib, SelectDocumentTypeView>(buildParams) {
    override fun onViewCreated(view: SelectDocumentTypeView, viewLifecycle: Lifecycle) {
        viewLifecycle.startStop {
            // brief but not very readable
            bind(view to rib.output using ViewEventToOutput)
        }
    }

    internal object ViewEventToOutput : (SelectDocumentTypeView.Event) -> Output? {
        override fun invoke(event: SelectDocumentTypeView.Event): Output? =
            when (event) {
                is IdCardSelected -> Output.DocumentTypeSelected(DocumentType.ID_CARD)
                is DrivingLicenseSelected -> Output.DocumentTypeSelected(DocumentType.DRIVING_LICENSE)
                is ResidencePermitSelected -> Output.DocumentTypeSelected(DocumentType.RESIDENCE_PERMIT)
                is PassportSelected -> Output.DocumentTypeSelected(DocumentType.PASSPORT)
            }
    }
}