package com.monu.ai.chat

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MonuStreamingController {

    private val _state = MutableStateFlow(MonuGenerationState())
    val state: StateFlow<MonuGenerationState> = _state.asStateFlow()

    fun start(conversationId: Long?, messageId: Long?) {
        _state.value = MonuGenerationState(
            status = MonuGenerationStatus.CONNECTING,
            conversationId = conversationId,
            messageId = messageId,
            startedAt = System.currentTimeMillis()
        )
    }

    fun generating() {
        _state.value = _state.value.copy(
            status = MonuGenerationStatus.GENERATING
        )
    }

    fun append(chunk: String) {
        _state.value = _state.value.copy(
            status = MonuGenerationStatus.GENERATING,
            partialText = _state.value.partialText + chunk
        )
    }

    fun complete() {
        _state.value = _state.value.copy(
            status = MonuGenerationStatus.COMPLETED
        )
    }

    fun stop() {
        _state.value = _state.value.copy(
            status = MonuGenerationStatus.STOPPED
        )
    }

    fun error(message: String) {
        _state.value = _state.value.copy(
            status = MonuGenerationStatus.ERROR,
            errorMessage = message
        )
    }

    fun reset() {
        _state.value = MonuGenerationState()
    }
}
