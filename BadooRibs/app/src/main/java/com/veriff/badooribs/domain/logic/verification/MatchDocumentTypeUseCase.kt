package com.veriff.badooribs.domain.logic.verification

import com.veriff.badooribs.domain.model.VerificationArtifact
import com.veriff.badooribs.domain.repo.VerificationRepository
import io.reactivex.Observable

class MatchDocumentTypeUseCase(
    private val repository: VerificationRepository
) {
    fun execute(artifact: VerificationArtifact.Photo): Observable<Boolean> {
        return repository.matchDocumentType(artifact)
    }
}