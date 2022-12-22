package com.veriff.badooribs.ribs.root.verification.feedback

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class InflowFeedbackPayload : Parcelable {
    @Parcelize
    object OnlyRetry : InflowFeedbackPayload()

    @Parcelize
    object RetryOrContinue : InflowFeedbackPayload()
}