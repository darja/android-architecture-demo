package com.veriff.badooribs.ribs.root.verification.takephoto

import com.badoo.ribs.core.Rib
import com.badoo.ribs.rx2.clienthelper.connector.Connectable
import com.veriff.badooribs.domain.model.VerificationArtifact
import com.veriff.badooribs.domain.model.VerificationTarget
import com.veriff.badooribs.ribs.RibDependency

interface TakePhotoRib : Rib, Connectable<TakePhotoRib.Input, TakePhotoRib.Output> {
    interface Dependency : RibDependency

    sealed class Input {
        data class ChangeTarget(val target: VerificationTarget.Photo) : Input()
    }

    sealed class Output {
        data class ArtifactProduced(val artifact: VerificationArtifact.Photo) : Output()
    }
}