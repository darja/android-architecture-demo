package com.veriff.badooribsflow.ribs.root

import com.badoo.ribs.core.Node
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.core.plugin.Plugin

internal class RootNode(
    buildParams: BuildParams<*>,
    plugins: List<Plugin> = emptyList(),
) : Node<Nothing>(
    buildParams = buildParams,
    viewFactory = null,
    plugins = plugins
), RootRib
