package com.veriff.badooribs.ribs.root.verification

import com.badoo.ribs.core.Node
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.core.plugin.Plugin
import com.badoo.ribs.rx2.clienthelper.connector.Connectable
import com.badoo.ribs.rx2.clienthelper.connector.NodeConnector
import com.veriff.badooribs.ribs.root.verification.VerificationFlowRib.Input
import com.veriff.badooribs.ribs.root.verification.VerificationFlowRib.Output

internal class VerificationFlowNode(
    buildParams: BuildParams<*>,
    plugins: List<Plugin> = emptyList(),
    connector: NodeConnector<Input, Output> = NodeConnector()
) :
    Node<Nothing>(
        buildParams = buildParams,
        viewFactory = null,
        plugins = plugins
    ),
    VerificationFlowRib,
    Connectable<Input, Output> by connector