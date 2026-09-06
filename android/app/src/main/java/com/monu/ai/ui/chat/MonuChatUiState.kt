package com.monu.ai.ui.chat

data class MonuChatUiState(
    val isAtBottom: Boolean = true,
    val showScrollToBottom: Boolean = false,
    val showGenerationIndicator: Boolean = false,
    val generationLabel: String = "",
    val canStopGeneration: Boolean = false,
    val showRetry: Boolean = false,
    val lastError: String? = null
)

class MonuChatUiController {

    fun onScroll(
        isAtBottom: Boolean
    ): MonuChatUiState {
        return MonuChatUiState(
            isAtBottom = isAtBottom,
            showScrollToBottom = !isAtBottom
        )
    }

    fun onGenerating(): MonuChatUiState {
        return MonuChatUiState(
            showGenerationIndicator = true,
            generationLabel = "MONU is thinking...",
            canStopGeneration = true
        )
    }

    fun onCompleted(): MonuChatUiState {
        return MonuChatUiState()
    }

    fun onError(message: String): MonuChatUiState {
        return MonuChatUiState(
            showRetry = true,
            lastError = message
        )
    }
}
