package com.veriff.badooribs.util

import android.util.Log
import io.reactivex.functions.Consumer

class LogConsumer(val tag: String, val source: String): Consumer<Any> {
    override fun accept(event: Any?) {
        Log.i(tag, "--dev $source: $event")
    }
}