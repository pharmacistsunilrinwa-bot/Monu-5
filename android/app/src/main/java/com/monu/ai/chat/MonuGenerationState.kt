package com.monu.ai.chat

enum class MonuGenerationStatus {
    IDLE,
    CONNECTING,
    GENERATING,
    COMPLETED,
    STOPPED,
    ERROR
}

data class MonuGenerationState(
    val status: MonuGenerationStatus = MonuGenerationStatus.IDLE,
    val conversationId: Long? = null,
    val messageId: Long? = null,
    val partialText: String = "",
    val startedAt: Long? = null,
    val errorMessage: String? = null
) {
    val isGenerating: Boolean
        get() = status == MonuGenerationStatus.GENERATING ||
                status == MonuGenerationStatus.CONNECTING
}
