package com.veriff.uberribs.ribs.intro

import com.uber.rib.core.Bundle
import com.uber.rib.core.EmptyPresenter
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import com.veriff.uberribs.domain.usecase.CheckUserConsentUseCase
import com.veriff.uberribs.domain.usecase.StartVerificationUseCase
import com.veriff.uberribs.ribs.intro.consent.ConsentBuilder
import com.veriff.uberribs.ribs.intro.standby.StandbyBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

import javax.inject.Inject

/**
 * Coordinates Business Logic for [IntroScope].
 */
@RibInteractor
class IntroInteractor : Interactor<EmptyPresenter, IntroRouter>(),
    StandbyBuilder.Listener,
    ConsentBuilder.Listener {

    @Inject
    protected lateinit var checkUserConsentUseCase: CheckUserConsentUseCase
    @Inject
    protected lateinit var startVerificationUseCase: StartVerificationUseCase
    @Inject
    protected lateinit var listener: IntroBuilder.Listener

    private val disposables = CompositeDisposable()

    override fun didBecomeActive(savedInstanceState: Bundle?) {
        super.didBecomeActive(savedInstanceState)

        checkUserConsent()
    }

    override fun willResignActive() {
        super.willResignActive()
        disposables.clear()
    }

    private fun checkUserConsent() {
        if (checkUserConsentUseCase.execute()) {
            router.attachConsent()
        } else {
            router.attachStandby()
        }
    }

    override fun onConsentAgreed() {
        router.detachConsent()
        router.attachStandby()
    }

    override fun onConsentDeclined() {
        listener.onCancelVerification()
    }

    override fun onReadyForVerification() {
        router.attachProgress()

        startVerificationUseCase.execute()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                router.detachStandby()
                router.detachProgress()
                listener.onStartVerification()
            }
            .addTo(disposables)
    }
}
