package com.veriff.badooribs.ribs.root.verification

import android.os.Parcelable
import com.badoo.ribs.core.Rib
import com.badoo.ribs.core.customisation.RibCustomisation
import com.badoo.ribs.routing.transition.handler.TransitionHandler
import com.badoo.ribs.rx2.clienthelper.connector.Connectable
import com.veriff.badooribs.domain.model.VerificationArtifact
import com.veriff.badooribs.domain.model.VerificationTarget
import com.veriff.badooribs.ribs.RibDependency
import com.veriff.badooribs.ribs.error.ErrorPayload
import kotlinx.parcelize.Parcelize

interface VerificationFlowRib : Rib,
    Connectable<VerificationFlowRib.Input, VerificationFlowRib.Output> {
    interface Dependency : RibDependency

    sealed class Configuration : Parcelable {
        @Parcelize
        class Error(val payload: ErrorPayload) : Configuration()

        @Parcelize
        object CheckPermission : Configuration()

        @Parcelize
        class TakePhoto(val target: VerificationTarget.Photo) : Configuration()

        @Parcelize
        class UndefinedDocumentType(val artifact: VerificationArtifact.Photo) : Configuration()

        @Parcelize
        class WrongDocumentType(val artifact: VerificationArtifact.Photo) : Configuration()
    }

    sealed class Input

    sealed class Output {
        object Success : Output()
        object Fail : Output()
    }

    class Customisation(
        val transitionHandler: TransitionHandler<Configuration>? = null,
    ) : RibCustomisation

}