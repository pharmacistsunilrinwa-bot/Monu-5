package com.monu.ai.network

import android.content.Context
import com.monu.ai.MonuRepository

data class MonuAiResponse(
    val primaryResponse: String,
    val routes: List<RouteResult>,
    val selectedModel: String
)

class MonuAiGateway(
    context: Context,
    repository: MonuRepository
) {

    private val router =
        MonuQuadRouter(
            context,
            repository
        )

    private val preferences =
        MonuModelPreferences(
            context
        )

    suspend fun process(
        message: String
    ): MonuAiResponse {

        val model =
            preferences.selectedModel()

        val result =
            router.route(
                message = message,
                model = model
            )

        val gemini =
            result.results.firstOrNull {
                it.route == "gemini" &&
                    it.success
            }

        val server =
            result.results.firstOrNull {
                it.route == "monu_server" &&
                    it.success
            }

        val wikipedia =
            result.results.firstOrNull {
                it.route == "wikipedia" &&
                    it.success
            }

        val primary =
            gemini?.response
                ?: server?.response
                ?: wikipedia?.response
                ?: result.results.joinToString(
                    "\n"
                ) {
                    "${it.route}: ${it.response}"
                }

        return MonuAiResponse(
            primaryResponse = primary,
            routes = result.results,
            selectedModel = model
        )
    }
}
