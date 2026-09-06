package com.monu.ai.integration

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class MonuEventDispatcher {

    private val _events =
        MutableSharedFlow<MonuAppEvent>(
            extraBufferCapacity = 64
        )

    val events: SharedFlow<MonuAppEvent> =
        _events

    fun dispatch(event: MonuAppEvent) {
        _events.tryEmit(event)
    }
}
