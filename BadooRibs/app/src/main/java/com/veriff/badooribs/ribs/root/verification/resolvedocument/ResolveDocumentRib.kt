package com.veriff.badooribs.ribs.root.verification.resolvedocument

import android.os.Parcelable
import com.badoo.ribs.core.Rib
import com.badoo.ribs.core.customisation.RibCustomisation
import com.badoo.ribs.routing.transition.handler.TransitionHandler
import com.badoo.ribs.rx2.clienthelper.connector.Connectable
import com.veriff.badooribs.domain.model.VerificationTarget
import com.veriff.badooribs.ribs.RibDependency
import kotlinx.parcelize.Parcelize

interface ResolveDocumentRib : Rib,
    Connectable<ResolveDocumentRib.Input, ResolveDocumentRib.Output> {
    interface Dependency : RibDependency

    sealed class Configuration : Parcelable {
        @Parcelize
        object SelectCountry : Configuration()

        @Parcelize
        object SelectDocumentType : Configuration()
    }

    sealed class Input

    sealed class Output {
        class Finish(val targetUpdate: List<VerificationTarget>) : Output()
    }

    class Customisation(
        val transitionHandler: TransitionHandler<Configuration>? = null,
    ) : RibCustomisation

}