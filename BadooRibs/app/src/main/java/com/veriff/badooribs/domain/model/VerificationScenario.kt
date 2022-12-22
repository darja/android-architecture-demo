package com.veriff.badooribs.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class VerificationScenario(
    val targets: List<VerificationTarget>,
    val partial: Boolean = false,
    val photosCount: Int = 1
): Parcelable