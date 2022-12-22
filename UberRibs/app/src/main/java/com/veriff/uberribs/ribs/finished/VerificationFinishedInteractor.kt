package com.veriff.uberribs.ribs.finished

import android.util.Log
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import javax.inject.Inject

/**
 * Coordinates Business Logic for [VerificationFinishedScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class VerificationFinishedInteractor :
    Interactor<VerificationFinishedInteractor.VerificationFinishedPresenter, VerificationFinishedRouter>() {

    @Inject
    lateinit var presenter: VerificationFinishedPresenter

    override fun didBecomeActive(savedInstanceState: Bundle?) {
        super.didBecomeActive(savedInstanceState)
        Log.d("VerificationFinished", "RIB attached")
    }

    override fun willResignActive() {
        super.willResignActive()
        Log.d("VerificationFinished", "RIB detached")
    }

    /**
     * Presenter interface implemented by this RIB's view.
     */
    interface VerificationFinishedPresenter
}
