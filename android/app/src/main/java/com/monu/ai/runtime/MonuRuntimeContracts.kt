package com.monu.ai.runtime

data class MonuRuntimeRequest(
    val conversationId: String? = null,
    val message: String,
    val modelId: String,
    val attachments: List<String> = emptyList(),
    val regenerate: Boolean = false
)

data class MonuRuntimeResponse(
    val requestId: String,
    val text: String,
    val modelId: String,
    val source: String,
    val completed: Boolean = true,
    val error: String? = null
)

enum class MonuRuntimeSource {
    GEMINI,
    SERVER,
    LOCAL_MEMORY,
    WIKIPEDIA,
    FALLBACK
}
