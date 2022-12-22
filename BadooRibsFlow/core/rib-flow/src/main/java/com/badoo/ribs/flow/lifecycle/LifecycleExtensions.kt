package com.badoo.ribs.flow.lifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

fun Lifecycle.whileAlive(action: suspend () -> Unit) {
    coroutineScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            action()
        }
    }
}

suspend fun <T> collect(connection: Pair<Flow<T>, FlowCollector<T>>) {
    connection.first.collect(connection.second)
}

infix fun <In, Out> Pair<Flow<In>, FlowCollector<Out>>.using(transformer: (In) -> Out) =
    first.map { transformer(it) } to second
