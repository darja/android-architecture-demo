package com.veriff.uberribs.ribs.intro.progress

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link FullscreenProgressBuilder.FullscreenProgressScope}.
 */
class FullscreenProgressRouter(
    view: FullscreenProgressView,
    interactor: FullscreenProgressInteractor,
    component: FullscreenProgressBuilder.Component
) : ViewRouter<FullscreenProgressView, FullscreenProgressInteractor>(view, interactor, component)
