package com.monu.ai.network

data class MonuModel(
    val id: String,
    val label: String,
    val enabled: Boolean = true
)

object MonuModelRegistry {

    /*
     * IDs are configuration candidates from the MONU blueprint.
     * Availability must be validated against the provider at final build time.
     */
    val models =
        listOf(
            MonuModel(
                "gemini-3.1-pro-preview",
                "Gemini 3.1 Pro Preview"
            ),
            MonuModel(
                "gemini-3-flash-preview",
                "Gemini 3 Flash Preview"
            ),
            MonuModel(
                "gemini-2.5-pro",
                "Gemini 2.5 Pro"
            ),
            MonuModel(
                "gemini-3.1-flash-lite",
                "Gemini 3.1 Flash Lite"
            ),
            MonuModel(
                "gemini-3.5-flash",
                "Gemini 3.5 Flash"
            ),
            MonuModel(
                "gemma-4-31b-it",
                "Gemma 4 31B IT"
            ),
            MonuModel(
                "gemma-4-26b-a4b-it",
                "Gemma 4 26B A4B IT"
            )
        )

    fun defaultModel(): MonuModel =
        models.first()
}
