package com.veriff.badooribs.ribs.checkpermission

import com.badoo.ribs.builder.Builder
import com.badoo.ribs.core.modality.BuildParams

internal class CheckPermissionBuilder(
    private val deps: CheckPermissionRib.Dependency,
) : Builder<CheckPermissionPayload, CheckPermissionRib>() {
    override fun build(buildParams: BuildParams<CheckPermissionPayload>): CheckPermissionRib {
        val interactor = CheckPermissionInteractor(
            deps.permissionRequester,
            buildParams
        )

        return CheckPermissionNode(
            buildParams = buildParams,
            plugins = listOf(interactor)
        )
    }
}