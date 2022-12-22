package com.veriff.badooribs.ribs.root.verification.takephoto

import android.os.Parcelable
import com.veriff.badooribs.domain.model.VerificationTarget
import kotlinx.parcelize.Parcelize

@Parcelize
class TakePhotoPayload(
    val target: VerificationTarget.Photo
) : Parcelable