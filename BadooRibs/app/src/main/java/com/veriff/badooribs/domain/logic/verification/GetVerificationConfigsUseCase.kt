package com.veriff.badooribs.domain.logic.verification

import com.veriff.badooribs.domain.repo.MockScenariosRepository

class GetVerificationConfigsUseCase(private val repository: MockScenariosRepository) {
    fun execute() = repository.getVerificationScenarios()
}