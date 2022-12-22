package com.veriff.badooribs.ribs.root.verification.takephoto

import com.badoo.ribs.core.Node
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.core.plugin.Plugin
import com.badoo.ribs.core.view.ViewFactory
import com.badoo.ribs.rx2.clienthelper.connector.Connectable
import com.badoo.ribs.rx2.clienthelper.connector.NodeConnector
import com.veriff.badooribs.ribs.root.verification.takephoto.TakePhotoRib.Input
import com.veriff.badooribs.ribs.root.verification.takephoto.TakePhotoRib.Output

internal class TakePhotoNode(
    buildParams: BuildParams<TakePhotoPayload>,
    viewFactory: ViewFactory<TakePhotoView>,
    plugins: List<Plugin>,
    connector: NodeConnector<Input, Output> = NodeConnector()
) :
    Node<TakePhotoView>(
        buildParams = buildParams,
        viewFactory = viewFactory,
        plugins = plugins
    ),
    TakePhotoRib,
    Connectable<Input, Output> by connector