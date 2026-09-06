package com.monu.ai.advanced

enum class MonuGenerationState {
    IDLE,
    QUEUED,
    GENERATING,
    STREAMING,
    COMPLETED,
    CANCELLED,
    ERROR
}

data class MonuGenerationSession(
    val id: String,
    val conversationId: Long?,
    val startedAt: Long = System.currentTimeMillis(),
    var state: MonuGenerationState =
        MonuGenerationState.IDLE
)

class MonuGenerationController {

    private var activeSession:
        MonuGenerationSession? = null

    fun start(
        conversationId: Long?
    ): MonuGenerationSession {

        val session =
            MonuGenerationSession(
                id = java.util.UUID
                    .randomUUID()
                    .toString(),
                conversationId = conversationId,
                state =
                    MonuGenerationState.GENERATING
            )

        activeSession = session

        return session
    }

    fun markStreaming() {
        activeSession?.state =
            MonuGenerationState.STREAMING
    }

    fun complete() {
        activeSession?.state =
            MonuGenerationState.COMPLETED
    }

    fun cancel() {
        activeSession?.state =
            MonuGenerationState.CANCELLED
    }

    fun active():
        MonuGenerationSession? =
        activeSession
}
