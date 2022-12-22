package com.veriff.uberribs.ribs.intro.consent

import android.view.View

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link ConsentBuilder.ConsentScope}.
 */
class ConsentRouter(
    view: ConsentView,
    interactor: ConsentInteractor,
    component: ConsentBuilder.Component
) : ViewRouter<ConsentView, ConsentInteractor>(
    view,
    interactor,
    component
)
