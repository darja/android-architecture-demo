package com.veriff.uberribs.domain.model

sealed class VerificationState {
    object Intro : VerificationState()
    object Finished : VerificationState()
}