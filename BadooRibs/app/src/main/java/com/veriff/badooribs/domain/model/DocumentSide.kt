package com.veriff.badooribs.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class DocumentSide: Parcelable {
    FRONT,
    BACK
}