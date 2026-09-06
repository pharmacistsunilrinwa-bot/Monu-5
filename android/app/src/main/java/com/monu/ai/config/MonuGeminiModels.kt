package com.monu.ai.config

data class MonuAiModel(
    val id: String,
    val displayName: String,
    val provider: String = "google"
)

object MonuGeminiModels {

    val supportedModels = listOf(

        MonuAiModel(
            id = "gemini-3.1-pro-preview",
            displayName = "Gemini 3.1 Pro Preview"
        ),

        MonuAiModel(
            id = "gemini-3-flash-preview",
            displayName = "Gemini 3 Flash Preview"
        ),

        MonuAiModel(
            id = "gemini-2.5-pro",
            displayName = "Gemini 2.5 Pro"
        ),

        MonuAiModel(
            id = "gemini-3.1-flash-lite",
            displayName = "Gemini 3.1 Flash Lite"
        ),

        MonuAiModel(
            id = "gemini-3.5-flash",
            displayName = "Gemini 3.5 Flash"
        ),

        MonuAiModel(
            id = "gemma-4-31b-it",
            displayName = "Gemma 4 31B IT"
        ),

        MonuAiModel(
            id = "gemma-4-26b-a4b-it",
            displayName = "Gemma 4 26B A4B IT"
        )
    )

    fun contains(
        modelId: String
    ): Boolean {

        return supportedModels.any {
            it.id == modelId
        }
    }

    fun fallback(): MonuAiModel {

        return supportedModels.firstOrNull {
            it.id == "gemini-2.5-pro"
        } ?: supportedModels.first()
    }
}
