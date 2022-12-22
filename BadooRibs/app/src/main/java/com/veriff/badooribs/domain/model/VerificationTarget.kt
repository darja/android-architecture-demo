package com.veriff.badooribs.domain.model

import android.os.Parcelable
import com.veriff.badooribs.util.classSimpleName
import kotlinx.parcelize.Parcelize

sealed class VerificationTarget : Parcelable {
    sealed class Photo : VerificationTarget() {
        @Parcelize
        object UnspecifiedDocument : Photo() {
            override fun toString(): String = classSimpleName
        }

        @Parcelize
        data class Document(
            val type: DocumentType,
            val side: DocumentSide,
        ) : Photo()

        @Parcelize
        object Selfie : Photo() {
            override fun toString(): String = classSimpleName
        }

        @Parcelize
        data class SelfieWithDocument(val documentType: DocumentType) : Photo()
    }

    @Parcelize
    object Nfc : VerificationTarget()
}