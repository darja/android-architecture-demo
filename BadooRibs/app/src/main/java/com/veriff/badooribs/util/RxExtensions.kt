package com.veriff.badooribs.util

import io.reactivex.Observable

fun <T> T?.toObservable(): Observable<T> =
    if (this == null) Observable.empty() else Observable.just(this)
