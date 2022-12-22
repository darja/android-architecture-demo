package com.veriff.badooribsflow.util

import android.util.Log
import android.view.View
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

fun <T> View.clicks(producer: () -> T): Flow<T> = callbackFlow {
    val value = producer()
    setOnClickListener {
        Log.i("--dev", "Click, produce an event: [${value?.classSimpleName}]")
        trySend(value)
    }
    awaitClose {
        Log.w("--dev", "Flow closed for: [$value]")
        setOnClickListener(null)
    }
}
