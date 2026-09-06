package com.monu.ai.network

import android.content.Context
import com.monu.ai.MonuRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class MonuQuadRouter(
    private val context: Context,
    private val repository: MonuRepository
) {

    private val network =
        MonuNetworkEngine(context)

    private val gemini =
        GeminiProvider(context)

    suspend fun route(
        message: String,
        model: String
    ): QuadRouteResult =
        coroutineScope {

            val server =
                async {
                    network.monuServer(message)
                }

            val wikipedia =
                async {
                    network.wikipedia(message)
                }

            val geminiResult =
                async {
                    gemini.generate(
                        message,
                        model
                    )
                }

            val memory =
                async {

                    try {

                        repository.remember(
                            key =
                                "last_message_" +
                                    System.currentTimeMillis(),
                            value = message
                        )

                        RouteResult(
                            route = "local_memory",
                            success = true,
                            response = "Stored",
                            latencyMs = 0
                        )

                    } catch (e: Exception) {

                        RouteResult(
                            route = "local_memory",
                            success = false,
                            response =
                                e.message ?: "Memory failed",
                            latencyMs = null
                        )
                    }
                }

            QuadRouteResult(
                listOf(
                    server.await(),
                    memory.await(),
                    geminiResult.await(),
                    wikipedia.await()
                )
            )
        }
}
