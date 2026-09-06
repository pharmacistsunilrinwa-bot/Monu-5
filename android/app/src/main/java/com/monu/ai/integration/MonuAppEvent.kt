package com.monu.ai.integration

sealed interface MonuAppEvent {

    data class UserMessage(
        val conversationId: String?,
        val message: String
    ) : MonuAppEvent

    data class Navigate(
        val destination: String
    ) : MonuAppEvent

    data class Regenerate(
        val conversationId: String?,
        val message: String
    ) : MonuAppEvent

    data class ConnectionChanged(
        val online: Boolean
    ) : MonuAppEvent

    data object StopGeneration : MonuAppEvent
}
