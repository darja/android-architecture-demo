package com.veriff.badooribs.ribs.checkpermission

import androidx.lifecycle.Lifecycle
import com.badoo.mvicore.android.lifecycle.createDestroy
import com.badoo.ribs.android.permissionrequester.PermissionRequester
import com.badoo.ribs.android.permissionrequester.PermissionRequester.RequestPermissionsEvent
import com.badoo.ribs.clienthelper.interactor.Interactor
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.rx2.adapter.rx2
import io.reactivex.functions.Consumer

internal class CheckPermissionInteractor(
    private val permissionRequester: PermissionRequester,
    private val buildParams: BuildParams<CheckPermissionPayload>,
) : Interactor<CheckPermissionRib, Nothing>(
    buildParams = buildParams
) {

    override fun onCreate(nodeLifecycle: Lifecycle) {
        super.onCreate(nodeLifecycle)
        nodeLifecycle.createDestroy {
            bind(
                permissionRequester.events(this@CheckPermissionInteractor)
                    .rx2() to permissionEventConsumer
            )
        }
        requestPermissions(buildParams.payload.permissions)
    }

    private fun requestPermissions(permissions: Array<String>) {
        permissionRequester.requestPermissions(
            client = this,
            requestCode = REQUEST_CODE_PERMISSION,
            permissions = permissions
        )
    }

    private val permissionEventConsumer: Consumer<RequestPermissionsEvent> =
        Consumer {
            when (it.requestCode) {
                REQUEST_CODE_PERMISSION -> when (it) {
                    is RequestPermissionsEvent.RequestPermissionsResult ->
                        if (it.granted.isNotEmpty()) {
                            rib.output.accept(CheckPermissionRib.Output.Granted)
                        } else {
                            rib.output.accept(CheckPermissionRib.Output.Denied)
                        }
                    is RequestPermissionsEvent.Cancelled -> rib.output.accept(CheckPermissionRib.Output.Denied)
                }
            }
        }

    companion object {
        private const val REQUEST_CODE_PERMISSION = 1
    }
}