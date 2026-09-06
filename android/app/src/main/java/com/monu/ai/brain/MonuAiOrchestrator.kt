package com.monu.ai.brain

import com.monu.ai.MonuCommandRouter
import com.monu.ai.network.MonuAiGateway

class MonuAiOrchestrator(
    private val commandRouter:
        MonuCommandRouter,
    private val moodController:
        MonuMoodController,
    private val executionPlanner:
        MonuExecutionPlanner,
    private val routeAggregator:
        MonuRouteAggregator,
    private val aiGateway:
        MonuAiGateway
) {

    suspend fun process(
        request: MonuAiRequest
    ): MonuAiResponse {

        val command =
            commandRouter.classify(
                request.message
            )

        val mood =
            moodController.detect(
                request.message
            )

        val plan =
            executionPlanner.plan(
                command.type
            )

        val startTime =
            System.currentTimeMillis()

        return try {

            val gatewayResponse =
                aiGateway.process(
                    message = request.message
                )

            val latency =
                System.currentTimeMillis() -
                    startTime

            val routeResults =
                listOf(
                    MonuRouteResult(
                        route =
                            "AI_GATEWAY",
                        success = true,
                        content =
                            gatewayResponse.primaryResponse,
                        latencyMs =
                            latency
                    )
                )

            val best =
                routeAggregator.aggregate(
                    routeResults
                )

            MonuAiResponse(
                answer =
                    best?.content
                        ?: "No response",
                model =
                    request.preferredModel,
                mood = mood,
                routes =
                    routeResults,
                diagnostics =
                    routeAggregator.diagnostics(
                        routeResults
                    ) + mapOf(
                        "command" to
                            command.type.name,
                        "targets" to
                            plan.targets.joinToString()
                    )
            )

        } catch (
            error: Exception
        ) {

            val failed =
                MonuRouteResult(
                    route =
                        "AI_GATEWAY",
                    success = false,
                    content =
                        error.message
                            ?: "Unknown error"
                )

            MonuAiResponse(
                answer =
                    "MONU could not complete the request.",
                model =
                    request.preferredModel,
                mood = mood,
                routes =
                    listOf(failed),
                diagnostics =
                    routeAggregator.diagnostics(
                        listOf(failed)
                    )
            )
        }
    }
}
