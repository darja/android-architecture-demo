package com.veriff.badooribs.domain.logic.takephoto

import android.util.Log
import com.veriff.badooribs.domain.model.VerificationArtifact
import com.veriff.badooribs.domain.model.VerificationTarget
import io.reactivex.Observable
import java.util.*
import java.util.concurrent.TimeUnit

class TakePhotoUseCase {
    fun execute(target: VerificationTarget.Photo): Observable<VerificationArtifact.Photo> =
        Observable.fromCallable {
            Log.d("--dev", "Take a photo")
            VerificationArtifact.Photo(target, UUID.randomUUID().toString())
        }
            .delay(1, TimeUnit.SECONDS)
}