package com.badoo.ribs.flow.connector

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class NodeConnector<Input, Output> : Connectable<Input, Output> {
    private val _input: MutableSharedFlow<Input> = MutableSharedFlow(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    override val input: Flow<Input> = _input

    override val output: MutableSharedFlow<Output> = MutableSharedFlow()
}
