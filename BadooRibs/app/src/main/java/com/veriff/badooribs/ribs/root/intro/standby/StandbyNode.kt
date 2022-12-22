package com.veriff.badooribs.ribs.root.intro.standby

import com.badoo.ribs.core.Node
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.core.plugin.Plugin
import com.badoo.ribs.core.view.ViewFactory
import com.badoo.ribs.rx2.clienthelper.connector.Connectable
import com.badoo.ribs.rx2.clienthelper.connector.NodeConnector
import com.veriff.badooribs.ribs.root.intro.standby.StandbyRib.Input
import com.veriff.badooribs.ribs.root.intro.standby.StandbyRib.Output

internal class StandbyNode(
    buildParams: BuildParams<*>,
    viewFactory: ViewFactory<StandbyView>,
    plugins: List<Plugin>,
    connector: NodeConnector<Input, Output> = NodeConnector()
) :
    Node<StandbyView>(
        buildParams = buildParams,
        viewFactory = viewFactory,
        plugins = plugins
    ),
    StandbyRib,
    Connectable<Input, Output> by connector