package com.veriff.uberribs.ribs.intro

import android.view.ViewGroup
import com.uber.rib.core.Router
import com.veriff.uberribs.ribs.intro.consent.ConsentBuilder
import com.veriff.uberribs.ribs.intro.consent.ConsentRouter
import com.veriff.uberribs.ribs.intro.progress.FullscreenProgressBuilder
import com.veriff.uberribs.ribs.intro.progress.FullscreenProgressRouter
import com.veriff.uberribs.ribs.intro.standby.StandbyBuilder
import com.veriff.uberribs.ribs.intro.standby.StandbyRouter

/**
 * Adds and removes children of {@link IntroBuilder.IntroScope}.
 */
class IntroRouter(
    private val parentView: ViewGroup,
    interactor: IntroInteractor,
    component: IntroBuilder.Component,
    private val standbyBuilder: StandbyBuilder,
    private val consentBuilder: ConsentBuilder,
    private val progressBuilder: FullscreenProgressBuilder,
) : Router<IntroInteractor>(interactor, component) {
    private var standbyRouter: StandbyRouter? = null
    private var consentRouter: ConsentRouter? = null
    private var progressRouter: FullscreenProgressRouter? = null

    val standby = DynamicState(
        viewBuilder = {standbyBuilder.build(parentView)},
        transition = {
            onAttach()
            onDetach()
        }
    )

    fun attachStandby() {
        standbyBuilder.build(parentView).also {
            attachChild(it)
            parentView.addView(it.view)
            standbyRouter = it
        }
    }

    fun detachStandby() {
        standbyRouter?.let {
            detachChild(it)
            parentView.removeView(it.view)
        }
        standbyRouter = null
    }

    fun attachConsent() {
        consentBuilder.build(parentView).also {
            attachChild(it)
            consentRouter = it
            parentView.addView(it.view)
        }
    }

    fun detachConsent() {
        consentRouter?.let {
            detachChild(it)
            parentView.removeView(it.view)
        }
        consentRouter = null
    }

    fun attachProgress() {
        progressBuilder.build(parentView).also {
            attachChild(it)
            parentView.addView(it.view)
            progressRouter = it
        }
    }

    fun detachProgress() {
        progressRouter?.let {
            detachChild(it)
            parentView.removeView(it.view)
        }
        progressRouter = null
    }
}
