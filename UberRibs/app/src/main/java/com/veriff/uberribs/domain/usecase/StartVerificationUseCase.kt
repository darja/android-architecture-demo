package com.veriff.uberribs.domain.usecase

import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class StartVerificationUseCase @Inject constructor() {
    fun execute(): Completable =
        Completable.timer(3, TimeUnit.SECONDS, Schedulers.computation())
}