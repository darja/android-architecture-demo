package com.veriff.badooribs.domain.logic.verification

import com.veriff.badooribs.domain.model.DocumentSide
import com.veriff.badooribs.domain.model.DocumentType
import com.veriff.badooribs.domain.model.VerificationTarget

class GetTargetsForDocumentTypeUseCase {
    fun execute(documentType: DocumentType): List<VerificationTarget> {
        return when (documentType) {
            DocumentType.ID_CARD,
            DocumentType.RESIDENCE_PERMIT,
            DocumentType.DRIVING_LICENSE,
            -> listOf(
                VerificationTarget.Photo.Document(documentType, DocumentSide.FRONT),
                VerificationTarget.Photo.Document(documentType, DocumentSide.BACK)
            )

            DocumentType.PASSPORT -> listOf(
                VerificationTarget.Photo.Document(documentType, DocumentSide.FRONT),
                VerificationTarget.Nfc,
            )
        }
    }
}