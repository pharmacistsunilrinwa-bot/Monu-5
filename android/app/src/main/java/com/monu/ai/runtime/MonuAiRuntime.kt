package com.monu.ai.runtime

import com.monu.ai.runtime.providers.MonuAiProvider
import java.util.UUID

class MonuAiRuntime {

    suspend fun execute(
        request: MonuRuntimeRequest,
        providerName: String,
        onPartial: suspend (String) -> Unit = {}
    ): MonuRuntimeResponse {

        val requestId = UUID.randomUUID().toString()

        val lifecycle =
            MonuRequestLifecycle(requestId)

        lifecycle.moveTo(
            MonuRequestStage.MEMORY_ANALYSIS
        )

        lifecycle.moveTo(
            MonuRequestStage.ROUTING
        )

        val provider: MonuAiProvider? =
            MonuProviderRegistry.get(providerName)

        if (provider == null) {
            lifecycle.fail("Provider unavailable")

            return MonuRuntimeFallback.create(
                requestId = requestId,
                modelId = request.modelId,
                reason = lifecycle.error ?: "Unknown provider error"
            )
        }

        if (
            MonuCancellationController
                .isCancelled(requestId)
        ) {
            lifecycle.cancel()

            return MonuRuntimeResponse(
                requestId = requestId,
                text = "",
                modelId = request.modelId,
                source = providerName,
                completed = false,
                error = "Generation cancelled"
            )
        }

        return try {

            lifecycle.moveTo(
                MonuRequestStage.GENERATING
            )

            val response =
                provider.generate(
                    request = request,
                    onPartial = onPartial
                )

            lifecycle.moveTo(
                MonuRequestStage.COMPLETED
            )

            response.copy(
                requestId = requestId
            )

        } catch (error: Exception) {

            lifecycle.fail(
                error.message ?: "Runtime generation error"
            )

            MonuRuntimeFallback.create(
                requestId = requestId,
                modelId = request.modelId,
                reason = lifecycle.error ?: "Unknown error"
            )
        } finally {
            MonuCancellationController.clear(requestId)
        }
    }
}
