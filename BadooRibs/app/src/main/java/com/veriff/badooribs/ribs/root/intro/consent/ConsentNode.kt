package com.veriff.badooribs.ribs.root.intro.consent

import com.badoo.ribs.core.Node
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.core.plugin.Plugin
import com.badoo.ribs.core.view.ViewFactory
import com.badoo.ribs.rx2.clienthelper.connector.Connectable
import com.badoo.ribs.rx2.clienthelper.connector.NodeConnector
import com.veriff.badooribs.ribs.root.intro.consent.ConsentRib.Input
import com.veriff.badooribs.ribs.root.intro.consent.ConsentRib.Output

internal class ConsentNode(
    buildParams: BuildParams<*>,
    viewFactory: ViewFactory<ConsentView>,
    plugins: List<Plugin>,
    connector: NodeConnector<Input, Output> = NodeConnector()
) :
    Node<ConsentView>(
        buildParams = buildParams,
        viewFactory = viewFactory,
        plugins = plugins
    ),
    ConsentRib,
    Connectable<Input, Output> by connector