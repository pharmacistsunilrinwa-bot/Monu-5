package com.monu.ai.network

import android.content.Context

/*
 * Provider abstraction.
 *
 * The exact Google GenAI endpoint/model availability is intentionally
 * configuration-driven rather than scattered across UI code.
 *
 * API key is supplied through secure BuildConfig injection in Phase 3.7.
 */
class GeminiProvider(
    private val context: Context
) {

    suspend fun generate(
        message: String,
        model: String
    ): RouteResult {

        val apiKey =
            try {
                context.packageManager
                    .getApplicationInfo(
                        context.packageName,
                        0
                    )
                ""
            } catch (_: Exception) {
                ""
            }

        if (apiKey.isBlank()) {

            return RouteResult(
                route = "gemini",
                success = false,
                response =
                    "Gemini API key not configured in this build",
                latencyMs = null
            )
        }

        return RouteResult(
            route = "gemini",
            success = false,
            response =
                "Gemini provider awaiting secure build configuration",
            latencyMs = null
        )
    }
}
