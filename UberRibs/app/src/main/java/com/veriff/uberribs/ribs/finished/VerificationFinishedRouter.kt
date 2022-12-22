package com.veriff.uberribs.ribs.finished

import android.view.View

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link VerificationFinishedBuilder.VerificationFinishedScope}.
 */
class VerificationFinishedRouter(
    view: VerificationFinishedView,
    interactor: VerificationFinishedInteractor,
    component: VerificationFinishedBuilder.Component
) : ViewRouter<VerificationFinishedView, VerificationFinishedInteractor>(
    view,
    interactor,
    component
)
