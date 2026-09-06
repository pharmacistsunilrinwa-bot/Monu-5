package com.monu.ai.runtime.providers

import com.monu.ai.runtime.MonuRuntimeRequest
import com.monu.ai.runtime.MonuRuntimeResponse

interface MonuAiProvider {
    val providerName: String

    suspend fun generate(
        request: MonuRuntimeRequest,
        onPartial: suspend (String) -> Unit = {}
    ): MonuRuntimeResponse
}
