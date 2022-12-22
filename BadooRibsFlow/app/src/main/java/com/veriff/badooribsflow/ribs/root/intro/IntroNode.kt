package com.veriff.badooribsflow.ribs.root.intro

import com.badoo.ribs.core.Node
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.core.plugin.Plugin
import com.badoo.ribs.core.view.ViewFactory
import com.badoo.ribs.flow.connector.Connectable
import com.badoo.ribs.flow.connector.NodeConnector
import com.veriff.badooribsflow.ribs.root.intro.IntroRib.Input
import com.veriff.badooribsflow.ribs.root.intro.IntroRib.Output

internal class IntroNode(
    buildParams: BuildParams<*>,
    viewFactory: ViewFactory<IntroView>,
    plugins: List<Plugin> = emptyList(),
    connector: NodeConnector<Input, Output> = NodeConnector()
) :
    Node<IntroView>(
        buildParams = buildParams,
        viewFactory = viewFactory,
        plugins = plugins
    ),
    IntroRib,
    Connectable<Input, Output> by connector
