package com.badoo.ribs.android.permissionrequester

import android.app.Activity
import android.content.pm.PackageManager
import androidx.fragment.app.Fragment
import com.badoo.ribs.android.permissionrequester.PermissionRequester.CheckPermissionsResult
import com.badoo.ribs.android.permissionrequester.PermissionRequester.RequestPermissionsEvent
import com.badoo.ribs.android.permissionrequester.PermissionRequester.RequestPermissionsEvent.Cancelled
import com.badoo.ribs.android.permissionrequester.PermissionRequester.RequestPermissionsEvent.RequestPermissionsResult
import com.badoo.ribs.android.requestcode.RequestCodeBasedEventStreamImpl
import com.badoo.ribs.android.requestcode.RequestCodeClient
import com.badoo.ribs.android.requestcode.RequestCodeRegistry

class PermissionRequestBoundary(
    private val permissionRequesterHost: PermissionRequesterHost,
    requestCodeRegistry: RequestCodeRegistry
) : RequestCodeBasedEventStreamImpl<RequestPermissionsEvent>(requestCodeRegistry),
    PermissionRequester,
    PermissionRequestResultHandler {

    constructor(
        activity: Activity,
        requestCodeRegistry: RequestCodeRegistry
    ) : this(
        PermissionRequesterHost.ActivityHost(activity),
        requestCodeRegistry
    )

    constructor(
        fragment: Fragment,
        requestCodeRegistry: RequestCodeRegistry
    ) : this(
        PermissionRequesterHost.FragmentHost(fragment),
        requestCodeRegistry
    )

    override fun checkPermissions(
        client: RequestCodeClient,
        permissions: Array<String>
    ): CheckPermissionsResult {
        val granted = mutableListOf<String>()
        val shouldShowRationale = mutableListOf<String>()
        val canAsk = mutableListOf<String>()

        permissions.forEach { permission ->
            val list = when {
                permissionRequesterHost.isGranted(permission) -> granted
                permissionRequesterHost.shouldShowRationale(permission) -> shouldShowRationale
                else -> canAsk
            }

            list += permission
        }

        return CheckPermissionsResult(
            granted, canAsk, shouldShowRationale
        )
    }

    override fun requestPermissions(
        client: RequestCodeClient,
        requestCode: Int,
        permissions: Array<String>
    ) {
        permissionRequesterHost.requestPermissions(
            client.forgeExternalRequestCode(requestCode),
            permissions
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (grantResults.isEmpty()) {
            onPermissionRequestCancelled(requestCode)

        } else {
            onPermissionRequestFinished(requestCode, permissions, grantResults)
        }
    }

    private fun onPermissionRequestCancelled(externalRequestCode: Int) {
        publish(
            externalRequestCode,
            Cancelled(
                requestCode = externalRequestCode.toInternalRequestCode()
            )
        )
    }

    private fun onPermissionRequestFinished(
        externalRequestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        val (granted, denied) = sortResults(permissions, grantResults)

        publish(
            externalRequestCode,
            RequestPermissionsResult(
                requestCode = externalRequestCode.toInternalRequestCode(),
                granted = granted,
                denied = denied
            )
        )
    }

    private fun sortResults(
        permissions: Array<out String>,
        grantResults: IntArray
    ): Pair<MutableList<String>, MutableList<String>> {
        val granted = mutableListOf<String>()
        val denied = mutableListOf<String>()

        permissions.forEachIndexed { index, permission ->
            if (grantResults[index] == PackageManager.PERMISSION_GRANTED) {
                granted.add(permission)
            } else {
                denied.add(permission)
            }
        }

        return granted to denied
    }
}
