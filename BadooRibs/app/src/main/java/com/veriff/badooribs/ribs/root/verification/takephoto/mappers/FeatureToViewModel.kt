package com.veriff.badooribs.ribs.root.verification.takephoto.mappers

import com.veriff.badooribs.domain.model.DocumentSide
import com.veriff.badooribs.domain.model.DocumentType
import com.veriff.badooribs.domain.model.VerificationTarget
import com.veriff.badooribs.ribs.root.verification.takephoto.TakePhotoFeature
import com.veriff.badooribs.ribs.root.verification.takephoto.TakePhotoView.ViewModel

internal class FeatureToViewModel : (TakePhotoFeature.State) -> ViewModel? {
    override fun invoke(input: TakePhotoFeature.State): ViewModel? {
        return when (input) {
            is TakePhotoFeature.State.Camera -> input.toViewModel()
            is TakePhotoFeature.State.ProcessingPhoto -> ViewModel.ProcessingPhoto
            else -> null
        }
    }

    private fun TakePhotoFeature.State.Camera.toViewModel(): ViewModel {
        return when (target) {
            is VerificationTarget.Photo.UnspecifiedDocument -> target.toViewModel()
            is VerificationTarget.Photo.Document -> target.toViewModel()
            is VerificationTarget.Photo.Selfie -> target.toViewModel()
            is VerificationTarget.Photo.SelfieWithDocument -> target.toViewModel()
        }
    }

    private fun VerificationTarget.Photo.UnspecifiedDocument.toViewModel(): ViewModel {
        return ViewModel.Document(
            title = "Scan your document",
            subtitle = "Please take a photo of your primary document",
            showFlip = false
        )
    }

    private fun VerificationTarget.Photo.Selfie.toViewModel(): ViewModel {
        return ViewModel.Selfie(
            title = "Selfie",
            subtitle = "Please take a selfie"
        )
    }

    private fun VerificationTarget.Photo.SelfieWithDocument.toViewModel(): ViewModel {
        return ViewModel.SelfieWithDocument(
            title = "Selfie",
            subtitle = "Please take a selfie with ${documentType}"
        )
    }

    private fun VerificationTarget.Photo.Document.toViewModel(): ViewModel {
        return when (type) {
            DocumentType.ID_CARD -> createIdCardViewModel(side)
            DocumentType.DRIVING_LICENSE -> createDrivingLicenseViewModel(side)
            DocumentType.PASSPORT -> createPassportViewModel()
            DocumentType.RESIDENCE_PERMIT -> createResidencePermitViewModel(side)
        }
    }

    private fun createResidencePermitViewModel(side: DocumentSide): ViewModel {
        return when (side) {
            DocumentSide.FRONT -> ViewModel.Document(
                title = "Residence permit",
                subtitle = "Please show your residence permit front side",
                showFlip = false
            )
            DocumentSide.BACK -> ViewModel.Document(
                title = "Residence permit",
                subtitle = "Please turn your residence permit",
                showFlip = true
            )
        }
    }

    private fun createIdCardViewModel(side: DocumentSide): ViewModel {
        return when (side) {
            DocumentSide.FRONT -> ViewModel.Document(
                title = "ID card",
                subtitle = "Please show your ID card front side",
                showFlip = false
            )
            DocumentSide.BACK -> ViewModel.Document(
                title = "ID card",
                subtitle = "Please turn your ID card",
                showFlip = true
            )
        }
    }

    private fun createDrivingLicenseViewModel(side: DocumentSide): ViewModel {
        return when (side) {
            DocumentSide.FRONT -> ViewModel.Document(
                title = "Driving license",
                subtitle = "Please show your driving license front side",
                showFlip = false
            )
            DocumentSide.BACK -> ViewModel.Document(
                title = "Driving license",
                subtitle = "Please turn your driving license",
                showFlip = true
            )
        }
    }

    private fun createPassportViewModel(): ViewModel {
        return ViewModel.Document(
            title = "Passport",
            subtitle = "Please show your passport",
            showFlip = false
        )
    }
}