package com.veriff.badooribs.ribs.root.verification

import com.veriff.badooribs.ribs.checkpermission.CheckPermissionBuilder
import com.veriff.badooribs.ribs.checkpermission.CheckPermissionRib
import com.veriff.badooribs.ribs.error.ErrorBuilder
import com.veriff.badooribs.ribs.error.ErrorRib
import com.veriff.badooribs.ribs.root.ResolveDocument.resolvedocument.ResolveDocumentBuilder
import com.veriff.badooribs.ribs.root.verification.resolvedocument.ResolveDocumentRib
import com.veriff.badooribs.ribs.root.verification.takephoto.TakePhotoBuilder
import com.veriff.badooribs.ribs.root.verification.takephoto.TakePhotoRib

internal class VerificationFlowChildBuilders(
    dependency: VerificationFlowRib.Dependency
) {
    private val subtreeDeps = SubtreeDependency(dependency)

    val takePhotoBuilder = TakePhotoBuilder(subtreeDeps)
    val checkCameraPermissionBuilder = CheckPermissionBuilder(subtreeDeps)
    val errorBuilder = ErrorBuilder(subtreeDeps)
    val resolveDocumentBuilder = ResolveDocumentBuilder(subtreeDeps)

    class SubtreeDependency(
        dependency: VerificationFlowRib.Dependency
    ) : VerificationFlowRib.Dependency by dependency,
        TakePhotoRib.Dependency,
        ErrorRib.Dependency,
        CheckPermissionRib.Dependency,
        ResolveDocumentRib.Dependency
}