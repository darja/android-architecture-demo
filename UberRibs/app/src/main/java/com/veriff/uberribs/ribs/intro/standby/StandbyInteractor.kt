package com.veriff.uberribs.ribs.intro.standby

import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import com.veriff.uberribs.domain.model.AnalyticsEvent
import com.veriff.uberribs.domain.usecase.PostAnalyticsEventUseCase
import com.veriff.uberribs.ribs.intro.standby.StandbyInteractor.StandbyPresenter.UiEvent
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

/**
 * Coordinates Business Logic for [StandbyScope].
 */
@RibInteractor
class StandbyInteractor : Interactor<StandbyInteractor.StandbyPresenter, StandbyRouter>() {
    @Inject protected lateinit var presenter: StandbyPresenter
    @Inject protected lateinit var listener: StandbyBuilder.Listener
    @Inject protected lateinit var postAnalyticsEventUseCase: PostAnalyticsEventUseCase

    private val disposables = CompositeDisposable()

    override fun didBecomeActive(savedInstanceState: Bundle?) {
        super.didBecomeActive(savedInstanceState)

        presenter.observeUiEvents()
            .subscribe { handleUiEvent(it) }
            .addTo(disposables)
    }

    override fun willResignActive() {
        super.willResignActive()
        disposables.clear()
    }

    private fun handleUiEvent(event: UiEvent) {
        when (event) {
            is UiEvent.ReadyForVerification -> {
                postAnalyticsEventUseCase.execute(AnalyticsEvent.VerificationStart)
                listener.onReadyForVerification()
            }
        }
    }

    /**
     * Presenter interface implemented by this RIB's view.
     */
    interface StandbyPresenter {
        fun observeUiEvents(): Observable<UiEvent>

        sealed class UiEvent {
            object ReadyForVerification: UiEvent()
        }
    }
}
