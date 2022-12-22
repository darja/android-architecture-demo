package com.veriff.uberribs.ribs.intro.progress

import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import javax.inject.Inject

/**
 * Coordinates Business Logic for [FullscreenProgressScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class FullscreenProgressInteractor :
    Interactor<FullscreenProgressInteractor.FullscreenProgressPresenter, FullscreenProgressRouter>() {

    @Inject
    lateinit var presenter: FullscreenProgressPresenter

    override fun didBecomeActive(savedInstanceState: Bundle?) {
        super.didBecomeActive(savedInstanceState)
    }

    override fun willResignActive() {
        super.willResignActive()
    }

    /**
     * Presenter interface implemented by this RIB's view.
     */
    interface FullscreenProgressPresenter
}
