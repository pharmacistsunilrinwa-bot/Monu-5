package com.monu.ai.integration

import android.content.Context

/**
 * Unified response actions.
 *
 * Copy
 * Speak
 * Share
 * Regenerate
 * Retry
 * Variant
 */
class MonuResponseIntegration(
    private val context: Context
) {

    enum class Action {
        COPY,
        SPEAK,
        SHARE,
        REGENERATE,
        RETRY,
        VARIANT
    }

    data class ActionRequest(
        val action: Action,
        val messageId: String,
        val content: String
    )

    data class ActionResult(
        val success: Boolean,
        val action: Action,
        val message: String
    )

    fun execute(
        request: ActionRequest
    ): ActionResult {

        return ActionResult(
            success = true,
            action = request.action,
            message = "Action ${request.action} dispatched"
        )
    }
}
