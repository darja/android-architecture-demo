package com.veriff.uberribs.ribs

import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import com.veriff.uberribs.domain.model.VerificationState
import com.veriff.uberribs.ribs.intro.IntroBuilder
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Coordinates Business Logic for [RootScope].
 */
@RibInteractor
class RootInteractor : Interactor<RootInteractor.RootPresenter, RootRouter>(),
    IntroBuilder.Listener {

    @Inject lateinit var presenter: RootPresenter

    private val disposables = CompositeDisposable()

    override fun didBecomeActive(savedInstanceState: Bundle?) {
        super.didBecomeActive(savedInstanceState)

        router.attachIntro()
    }

    override fun willResignActive() {
        super.willResignActive()

        disposables.clear()
    }

    override fun onStartVerification() {
        router.detachIntro()
        router.attachFinished()
    }

    override fun onCancelVerification() {
        presenter.showVerificationCancelled()
    }

    /**
     * Presenter interface implemented by this RIB's view.
     */
    interface RootPresenter {
        fun showVerificationCancelled()
    }
}
