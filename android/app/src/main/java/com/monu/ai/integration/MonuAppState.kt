package com.monu.ai.integration

data class MonuAppState(
    val initialized: Boolean = false,
    val generating: Boolean = false,
    val connected: Boolean = false,
    val currentConversationId: String? = null,
    val currentModelId: String = "",
    val lastError: String? = null
)
