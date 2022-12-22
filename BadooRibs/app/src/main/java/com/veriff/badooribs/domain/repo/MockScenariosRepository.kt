package com.veriff.badooribs.domain.repo

import com.veriff.badooribs.domain.model.*
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class MockScenariosRepository {
    fun getVerificationScenarios(): Observable<List<MockVerificationConfig>> {
        return Observable.just(listOf(
            MockVerificationConfig(
                title = "Happy scenario",
                description = "✓ Id card (matched properly)\n✓ Selfie\n✓ Document with selfie",
                resolvedDocumentType = DocumentType.ID_CARD,
                scenario = VerificationScenario(
                    targets = listOf(
                        VerificationTarget.Photo.Document(DocumentType.ID_CARD, DocumentSide.FRONT),
                        VerificationTarget.Photo.Document(DocumentType.ID_CARD, DocumentSide.BACK),
                        VerificationTarget.Photo.Selfie,
                        VerificationTarget.Photo.SelfieWithDocument(DocumentType.ID_CARD),
                    )
                )
            ),
            MockVerificationConfig(
                title = "Unspecified document, resolved as driving license",
                description = "✓ Unspecified document (matched as driving license)\n✓ Selfie",
                resolvedDocumentType = DocumentType.DRIVING_LICENSE,
                scenario = VerificationScenario(
                    targets = listOf(
                        VerificationTarget.Photo.UnspecifiedDocument,
                        VerificationTarget.Photo.Selfie,
                    )
                ),
            ),
            MockVerificationConfig(
                title = "Unspecified document, not resolved",
                description = "• Unspecified document (not resolved)\n✓ Selfie",
                resolvedDocumentType = null,
                scenario = VerificationScenario(
                    targets = listOf(
                        VerificationTarget.Photo.UnspecifiedDocument,
                        VerificationTarget.Photo.Selfie,
                    )
                ),
            ),
            // todo not handled yet
            MockVerificationConfig(
                title = "Document type not matching",
                description = "✓ Id card (matched as residence permit)\n✓ Selfie\n✓ Document with selfie",
                resolvedDocumentType = DocumentType.RESIDENCE_PERMIT,
                scenario = VerificationScenario(
                    targets = listOf(
                        VerificationTarget.Photo.Document(DocumentType.ID_CARD, DocumentSide.FRONT),
                        VerificationTarget.Photo.Document(DocumentType.ID_CARD, DocumentSide.BACK),
                        VerificationTarget.Photo.Selfie,
                        VerificationTarget.Photo.SelfieWithDocument(DocumentType.ID_CARD),
                    )
                ),
            ),
        ))
            .delay(1, TimeUnit.SECONDS)
    }
}