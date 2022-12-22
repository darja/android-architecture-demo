package com.veriff.uberribs.ribs.intro.standby

import android.view.View

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link StandbyBuilder.StandbyScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class StandbyRouter(
    view: StandbyView,
    interactor: StandbyInteractor,
    component: StandbyBuilder.Component
) : ViewRouter<StandbyView, StandbyInteractor>(view, interactor, component)
