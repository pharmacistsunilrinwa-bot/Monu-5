package com.monu.ai.integration

import android.content.Context
import com.monu.ai.MonuBrain
import com.monu.ai.MonuCommandRouter

/**
 * Central integration gateway.
 *
 * This class is the bridge between UI, AI brain,
 * commands, memory, network and Android features.
 */
class MonuFeatureIntegration(
    private val context: Context
) {

    private val brain = MonuBrain()
    private val commandRouter = MonuCommandRouter()

    data class IntegrationResult(
        val success: Boolean,
        val route: String,
        val message: String,
        val metadata: Map<String, String> = emptyMap()
    )

    fun processUserInput(
        input: String
    ): IntegrationResult {

        if (input.isBlank()) {
            return IntegrationResult(
                success = false,
                route = "validation",
                message = "Message cannot be empty"
            )
        }

        return try {
            val decision = brain.process(input)

            IntegrationResult(
                success = true,
                route = "central_ai_brain",
                message = input,
                metadata = mapOf(
                    "brain" to decision.toString(),
                    "source" to "apk"
                )
            )
        } catch (error: Exception) {
            IntegrationResult(
                success = false,
                route = "error_recovery",
                message = error.message ?: "Unknown integration error"
            )
        }
    }
}
