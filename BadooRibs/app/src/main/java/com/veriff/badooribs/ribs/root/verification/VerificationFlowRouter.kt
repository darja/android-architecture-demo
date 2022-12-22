package com.veriff.badooribs.ribs.root.verification

import android.Manifest
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.routing.Routing
import com.badoo.ribs.routing.resolution.ChildResolution.Companion.child
import com.badoo.ribs.routing.resolution.Resolution
import com.badoo.ribs.routing.router.Router
import com.badoo.ribs.routing.source.RoutingSource
import com.badoo.ribs.routing.transition.handler.TransitionHandler
import com.veriff.badooribs.ribs.checkpermission.CheckPermissionPayload
import com.veriff.badooribs.ribs.root.verification.VerificationFlowRib.Configuration.*
import com.veriff.badooribs.ribs.root.verification.takephoto.TakePhotoPayload

internal class VerificationFlowRouter(
    buildParams: BuildParams<*>,
    routingSource: RoutingSource<VerificationFlowRib.Configuration>,
    private val builders: VerificationFlowChildBuilders,
    transitionHandler: TransitionHandler<VerificationFlowRib.Configuration>? = null
) : Router<VerificationFlowRib.Configuration>(
    buildParams = buildParams,
    routingSource = routingSource,
    transitionHandler = transitionHandler
) {

    override fun resolve(routing: Routing<VerificationFlowRib.Configuration>): Resolution {
        with (builders) {
            return when (val configuration = routing.configuration) {
                is TakePhoto -> attachTakePhoto(configuration)
                is CheckPermission -> attachCheckPermission()
                is Error -> attachError(configuration)
                is WrongDocumentType -> attachResolveDocument()
                is UndefinedDocumentType -> attachResolveDocument()
            }
        }
    }

    private fun VerificationFlowChildBuilders.attachTakePhoto(configuration: TakePhoto) = child {
        takePhotoBuilder.build(
            buildContext = it,
            payload = TakePhotoPayload(configuration.target)
        )
    }

    private fun VerificationFlowChildBuilders.attachCheckPermission() = child {
        checkCameraPermissionBuilder.build(
            buildContext = it,
            payload = CheckPermissionPayload(arrayOf(Manifest.permission.CAMERA))
        )
    }

    private fun VerificationFlowChildBuilders.attachError(configuration: Error) = child {
        errorBuilder.build(
            buildContext = it,
            payload = configuration.payload
        )
    }

    private fun VerificationFlowChildBuilders.attachResolveDocument() = child {
        resolveDocumentBuilder.build(it)
    }
}