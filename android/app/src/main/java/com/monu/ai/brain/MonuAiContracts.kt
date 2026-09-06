package com.monu.ai.brain

import com.monu.ai.MonuCommandType

enum class MonuMood {
    NEUTRAL,
    FOCUSED,
    CREATIVE,
    CALM,
    ALERT
}

data class MonuAiRequest(
    val message: String,
    val conversationId: Long? = null,
    val preferredModel: String? = null,
    val commandType: MonuCommandType? = null,
    val attachments: List<String> = emptyList()
)

data class MonuRouteResult(
    val route: String,
    val success: Boolean,
    val content: String,
    val latencyMs: Long = 0L
)

data class MonuAiResponse(
    val answer: String,
    val model: String?,
    val mood: MonuMood,
    val routes: List<MonuRouteResult>,
    val diagnostics: Map<String, String> = emptyMap()
)
