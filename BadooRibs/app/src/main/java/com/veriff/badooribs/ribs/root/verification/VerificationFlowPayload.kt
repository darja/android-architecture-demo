package com.veriff.badooribs.ribs.root.verification

import android.os.Parcelable
import com.veriff.badooribs.domain.model.VerificationScenario
import kotlinx.parcelize.Parcelize

@Parcelize
class VerificationFlowPayload(
    val scenario: VerificationScenario
): Parcelable