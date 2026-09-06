package com.monu.ai.integration

import com.monu.ai.runtime.MonuAiRuntime
import com.monu.ai.runtime.MonuRuntimeRequest
import com.monu.ai.runtime.MonuRuntimeResponse

class MonuRuntimeBridge(
    private val runtime: MonuAiRuntime
) {

    suspend fun send(
        conversationId: String?,
        message: String,
        modelId: String,
        provider: String,
        onPartial: suspend (String) -> Unit = {}
    ): MonuRuntimeResponse {

        val request = MonuRuntimeRequest(
            conversationId = conversationId,
            message = message,
            modelId = modelId
        )

        return runtime.execute(
            request = request,
            providerName = provider,
            onPartial = onPartial
        )
    }
}
