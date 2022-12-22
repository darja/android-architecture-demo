package com.veriff.uberribs.domain.repo

import com.jakewharton.rxrelay2.BehaviorRelay
import com.veriff.uberribs.domain.model.VerificationState
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class VerificationRepository @Inject constructor() {
    private val stateRelay = BehaviorRelay.createDefault<VerificationState>(VerificationState.Intro)

    fun observeState(): Observable<VerificationState> = stateRelay

    fun setState(state: VerificationState) {
        stateRelay.accept(state)
    }
}