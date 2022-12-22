package com.veriff.badooribs.ribs.root.verification.resolvedocument.selectcountry

import com.badoo.ribs.core.Node
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.core.plugin.Plugin
import com.badoo.ribs.core.view.ViewFactory
import com.badoo.ribs.rx2.clienthelper.connector.Connectable
import com.badoo.ribs.rx2.clienthelper.connector.NodeConnector

internal class SelectCountryNode(
    buildParams: BuildParams<*>,
    viewFactory: ViewFactory<SelectCountryView>,
    plugins: List<Plugin>,
    connector: NodeConnector<SelectCountryRib.Input, SelectCountryRib.Output> = NodeConnector()
) :
    Node<SelectCountryView>(
        buildParams = buildParams,
        viewFactory = viewFactory,
        plugins = plugins
    ),
    SelectCountryRib,
    Connectable<SelectCountryRib.Input, SelectCountryRib.Output> by connector