package com.veriff.badooribs.ribs.error

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class ErrorPayload(
    val title: String,
    val text: String
) : Parcelable {
    @Parcelize
    object VerificationError :
        ErrorPayload("Verification Error", "Sorry, we cannot verify you")

    @Parcelize
    object PermissionError : ErrorPayload("No Permission", "We need camera permission to work")

    @Parcelize
    object VerificationCancelled : ErrorPayload("Verification cancelled", "Try again later")
}