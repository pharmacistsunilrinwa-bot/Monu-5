package com.monu.ai.runtime

object MonuRuntimeFallback {

    fun create(
        requestId: String,
        modelId: String,
        reason: String
    ): MonuRuntimeResponse {

        return MonuRuntimeResponse(
            requestId = requestId,
            text = "MONU is temporarily unable to reach the selected AI route.",
            modelId = modelId,
            source = MonuRuntimeSource.FALLBACK.name,
            completed = false,
            error = reason
        )
    }
}
