package com.veriff.badooribs.domain.logic.verification

import com.veriff.badooribs.domain.model.VerificationArtifact
import com.veriff.badooribs.domain.model.VerificationTarget
import com.veriff.badooribs.domain.repo.VerificationRepository
import io.reactivex.Observable

class ResolveDocumentTypeUseCase(
    private val repository: VerificationRepository,
    private val getTargetsForDocumentTypeUseCase: GetTargetsForDocumentTypeUseCase = GetTargetsForDocumentTypeUseCase(),
) {
    fun execute(artifact: VerificationArtifact.Photo): Observable<List<VerificationTarget>> =
        repository.resolveDocumentType(artifact)
            .map { getTargetsForDocumentTypeUseCase.execute(it) }
}