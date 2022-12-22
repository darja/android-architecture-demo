package com.veriff.badooribs.domain.repo

import android.util.Log
import com.veriff.badooribs.domain.model.DocumentType
import com.veriff.badooribs.domain.model.VerificationArtifact
import com.veriff.badooribs.domain.model.VerificationTarget
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class VerificationRepository {
    private var resolvedDocumentType: DocumentType? = null

    fun setup(resolvedDocumentType: DocumentType?) {
        Log.d("--dev", "Update document type: $resolvedDocumentType")
        this.resolvedDocumentType = resolvedDocumentType
    }

    fun resolveDocumentType(artifact: VerificationArtifact): Observable<DocumentType> =
        Observable.fromCallable {
            resolvedDocumentType ?: throw IllegalStateException("Document type not resolved")
        }
            .delay(1, TimeUnit.SECONDS)

    fun matchDocumentType(artifact: VerificationArtifact.Photo): Observable<Boolean> =
        Observable.fromCallable {
            (artifact.target as? VerificationTarget.Photo)?.let {
                it is VerificationTarget.Photo.Document && it.type == resolvedDocumentType
            } ?: false
        }
            .delay(1, TimeUnit.SECONDS)
}