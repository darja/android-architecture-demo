package com.veriff.badooribs.domain.logic.verification

import com.veriff.badooribs.domain.model.MockVerificationConfig
import com.veriff.badooribs.domain.repo.VerificationRepository
import io.reactivex.Completable

class ApplyVerificationConfigUseCase(private val repository: VerificationRepository) {
    fun execute(config: MockVerificationConfig): Completable = Completable.fromAction {
        repository.setup(config.resolvedDocumentType)
    }
}