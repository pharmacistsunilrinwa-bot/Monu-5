package com.monu.ai.integration

import android.content.Context

/**
 * Connects chat UI with Central AI Brain.
 *
 * UI
 *  ↓
 * Chat Integration
 *  ↓
 * AI Gateway
 *  ↓
 * Quad Router
 *  ↓
 * Gemini / Server / Wikipedia / Memory
 */
class MonuChatIntegration(
    context: Context
) {

    private val integration = MonuFeatureIntegration(context)

    data class ChatResult(
        val response: String,
        val route: String,
        val success: Boolean
    )

    fun send(message: String): ChatResult {

        val result = integration.processUserInput(message)

        return ChatResult(
            response = result.message,
            route = result.route,
            success = result.success
        )
    }
}
