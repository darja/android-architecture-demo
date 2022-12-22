package com.veriff.badooribs.ribs.root.verification.resolvedocument.documenttype

import com.badoo.ribs.core.Node
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.core.plugin.Plugin
import com.badoo.ribs.core.view.ViewFactory
import com.badoo.ribs.rx2.clienthelper.connector.Connectable
import com.badoo.ribs.rx2.clienthelper.connector.NodeConnector

internal class SelectDocumentTypeNode(
    buildParams: BuildParams<*>,
    viewFactory: ViewFactory<SelectDocumentTypeView>,
    plugins: List<Plugin>,
    connector: NodeConnector<SelectDocumentTypeRib.Input, SelectDocumentTypeRib.Output> = NodeConnector()
) :
    Node<SelectDocumentTypeView>(
        buildParams = buildParams,
        viewFactory = viewFactory,
        plugins = plugins
    ),
    SelectDocumentTypeRib,
    Connectable<SelectDocumentTypeRib.Input, SelectDocumentTypeRib.Output> by connector