package com.veriff.badooribs.ribs.checkpermission

import com.badoo.ribs.core.Node
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.core.plugin.Plugin
import com.badoo.ribs.rx2.clienthelper.connector.Connectable
import com.badoo.ribs.rx2.clienthelper.connector.NodeConnector
import com.veriff.badooribs.ribs.checkpermission.CheckPermissionRib.Input
import com.veriff.badooribs.ribs.checkpermission.CheckPermissionRib.Output

internal class CheckPermissionNode(
    buildParams: BuildParams<CheckPermissionPayload>,
    plugins: List<Plugin> = emptyList(),
    connector: NodeConnector<Input, Output> = NodeConnector()
) :
    Node<Nothing>(
        buildParams = buildParams,
        viewFactory = null,
        plugins = plugins
    ),
    CheckPermissionRib,
    Connectable<Input, Output> by connector