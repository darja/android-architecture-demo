package com.veriff.uberribs.ribs.intro.consent

import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import com.veriff.uberribs.domain.model.AnalyticsEvent
import com.veriff.uberribs.domain.usecase.PostAnalyticsEventUseCase
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

/**
 * Coordinates Business Logic for [ConsentScope].
 */
@RibInteractor
class ConsentInteractor : Interactor<ConsentInteractor.ConsentPresenter, ConsentRouter>() {

    @Inject protected lateinit var presenter: ConsentPresenter
    @Inject protected lateinit var listener: ConsentBuilder.Listener
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

    private fun handleUiEvent(event: ConsentPresenter.UiEvent) {
        when (event) {
            is ConsentPresenter.UiEvent.Agree -> {
                postAnalyticsEventUseCase.execute(AnalyticsEvent.ConsentAgreed)
                listener.onConsentAgreed()
            }

            is ConsentPresenter.UiEvent.Decline -> {
                postAnalyticsEventUseCase.execute(AnalyticsEvent.ConsentDeclined)
                listener.onConsentDeclined()
            }
        }
    }

    /**
     * Presenter interface implemented by this RIB's view.
     */
    interface ConsentPresenter {
        fun observeUiEvents(): Observable<UiEvent>

        sealed class UiEvent {
            object Agree: UiEvent()
            object Decline: UiEvent()
        }
    }
}
