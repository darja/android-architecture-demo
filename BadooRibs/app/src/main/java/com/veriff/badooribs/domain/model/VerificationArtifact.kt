package com.veriff.badooribs.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class VerificationArtifact: Parcelable {
    @Parcelize
    data class Photo(
        val target: VerificationTarget.Photo,
        val fileName: String,
    ) : VerificationArtifact()

    @Parcelize
    data class Nfc(
        val target: VerificationTarget.Nfc,
    ): VerificationArtifact()
}