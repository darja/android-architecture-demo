package com.badoo.ribs.flow.connector

import android.icu.util.Output
import com.badoo.ribs.core.plugin.NodeLifecycleAware
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

interface Connectable<Input, Output> : NodeLifecycleAware {
    val input: Flow<Input>
    val output: MutableSharedFlow<Output>
}

