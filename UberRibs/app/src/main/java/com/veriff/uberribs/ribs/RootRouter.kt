package com.veriff.uberribs.ribs

import android.view.View

import com.uber.rib.core.ViewRouter
import com.veriff.uberribs.ribs.finished.VerificationFinishedBuilder
import com.veriff.uberribs.ribs.finished.VerificationFinishedView
import com.veriff.uberribs.ribs.intro.IntroBuilder
import com.veriff.uberribs.ribs.intro.IntroRouter

/**
 * Adds and removes children of {@link RootBuilder.RootScope}.
 */
class RootRouter(
    view: RootView,
    interactor: RootInteractor,
    component: RootBuilder.Component,
    private val introBuilder: IntroBuilder,
    private val verificationFinishedBuilder: VerificationFinishedBuilder,
) : ViewRouter<RootView, RootInteractor>(view, interactor, component) {
    private var introRouter: IntroRouter? = null

    fun attachIntro() {
        introRouter = introBuilder.build(view).also {
            attachChild(it)
        }
    }

    fun detachIntro() {
        introRouter?.let { detachChild(it) }
    }

    fun attachFinished() {
        introRouter?.let { detachChild(it) }
        introRouter = null
        verificationFinishedBuilder.build(view).also {
            attachChild(it)
            view.addView(it.view)
        }
    }
}
