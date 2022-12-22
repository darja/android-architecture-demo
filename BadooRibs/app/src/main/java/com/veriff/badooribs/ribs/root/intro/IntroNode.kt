package com.veriff.badooribs.ribs.root.intro

import com.badoo.ribs.core.Node
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.core.plugin.Plugin
import com.badoo.ribs.rx2.clienthelper.connector.Connectable
import com.badoo.ribs.rx2.clienthelper.connector.NodeConnector
import com.veriff.badooribs.ribs.root.intro.IntroRib.Input
import com.veriff.badooribs.ribs.root.intro.IntroRib.Output

internal class IntroNode(
    buildParams: BuildParams<*>,
    plugins: List<Plugin> = emptyList(),
    connector: NodeConnector<Input, Output> = NodeConnector()
) :
    Node<Nothing>(
        buildParams = buildParams,
        viewFactory = null,
        plugins = plugins
    ),
    IntroRib,
    Connectable<Input, Output> by connector