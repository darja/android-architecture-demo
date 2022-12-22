package com.veriff.badooribs.ribs.error

import com.badoo.ribs.core.Node
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.core.plugin.Plugin
import com.badoo.ribs.core.view.ViewFactory

class ErrorNode(
    buildParams: BuildParams<*>,
    viewFactory: ViewFactory<ErrorView>,
    plugins: List<Plugin>
) : Node<ErrorView>(
    buildParams = buildParams,
    viewFactory = viewFactory,
    plugins = plugins
), ErrorRib