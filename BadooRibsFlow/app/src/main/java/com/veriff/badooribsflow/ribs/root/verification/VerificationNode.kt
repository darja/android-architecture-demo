package com.veriff.badooribsflow.ribs.root.verification

import com.badoo.ribs.core.Node
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.core.plugin.Plugin
import com.badoo.ribs.core.view.ViewFactory
import com.badoo.ribs.flow.connector.Connectable
import com.badoo.ribs.flow.connector.NodeConnector

internal class VerificationNode(
    buildParams: BuildParams<*>,
    viewFactory: ViewFactory<VerificationView>,
    plugins: List<Plugin> = emptyList(),
    connector: NodeConnector<VerificationRib.Input, VerificationRib.Output> = NodeConnector()
) :
    Node<VerificationView>(
        buildParams = buildParams,
        viewFactory = viewFactory,
        plugins = plugins
    ),
    VerificationRib,
    Connectable<VerificationRib.Input, VerificationRib.Output> by connector
