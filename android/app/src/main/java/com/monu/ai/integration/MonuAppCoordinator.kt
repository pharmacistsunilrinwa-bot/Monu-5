package com.monu.ai.integration

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MonuAppCoordinator {

    private val _state =
        MutableStateFlow(MonuAppState())

    val state: StateFlow<MonuAppState> =
        _state

    fun initialize(
        modelId: String
    ) {
        _state.value =
            _state.value.copy(
                initialized = true,
                currentModelId = modelId
            )
    }

    fun generationStarted() {
        _state.value =
            _state.value.copy(
                generating = true,
                lastError = null
            )
    }

    fun generationFinished() {
        _state.value =
            _state.value.copy(
                generating = false
            )
    }

    fun connectionChanged(
        connected: Boolean
    ) {
        _state.value =
            _state.value.copy(
                connected = connected
            )
    }

    fun conversationChanged(
        conversationId: String?
    ) {
        _state.value =
            _state.value.copy(
                currentConversationId = conversationId
            )
    }

    fun reportError(
        message: String
    ) {
        _state.value =
            _state.value.copy(
                generating = false,
                lastError = message
            )
    }
}
