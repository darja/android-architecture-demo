package com.veriff.badooribs.ribs.root.verification.feedback

import com.badoo.ribs.core.Node
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.core.plugin.Plugin
import com.badoo.ribs.core.view.ViewFactory
import com.badoo.ribs.rx2.clienthelper.connector.Connectable
import com.badoo.ribs.rx2.clienthelper.connector.NodeConnector

internal class InflowFeedbackNode(
    buildParams: BuildParams<*>,
    viewFactory: ViewFactory<InflowFeedbackView>,
    plugins: List<Plugin>,
    connector: NodeConnector<InflowFeedbackRib.Input, InflowFeedbackRib.Output> = NodeConnector()

) : Node<InflowFeedbackView>(
    buildParams = buildParams,
    viewFactory = viewFactory,
    plugins = plugins
),
    InflowFeedbackRib,
    Connectable<InflowFeedbackRib.Input, InflowFeedbackRib.Output> by connector