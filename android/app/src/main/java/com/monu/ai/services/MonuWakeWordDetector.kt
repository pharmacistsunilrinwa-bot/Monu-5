package com.monu.ai.services

data class MonuWakeWordResult(
    val detected: Boolean,
    val phrase: String?
)

class MonuWakeWordDetector {

    private val wakePhrases =
        setOf(
            "hello monu",
            "hey monu",
            "हेलो मोनू",
            "हे मोनू"
        )

    fun analyze(
        recognizedText: String
    ): MonuWakeWordResult {

        val normalized =
            recognizedText
                .lowercase()
                .trim()

        val phrase =
            wakePhrases.firstOrNull {
                it in normalized
            }

        return MonuWakeWordResult(
            detected =
                phrase != null,
            phrase = phrase
        )
    }
}
