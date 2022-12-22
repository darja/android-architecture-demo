package com.veriff.uberribs.domain.usecase

import javax.inject.Inject

class CheckUserConsentUseCase @Inject constructor() {
    fun execute(): Boolean {
        return System.currentTimeMillis() % 2 == 1L
    }
}