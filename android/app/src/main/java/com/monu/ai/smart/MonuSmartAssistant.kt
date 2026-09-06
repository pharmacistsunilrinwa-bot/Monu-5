package com.monu.ai.smart

data class MonuSuggestion(
    val id: String,
    val label: String,
    val prompt: String
)

data class MonuResponseMetadata(
    val model: String? = null,
    val latencyMs: Long? = null,
    val route: String? = null,
    val generatedAt: Long = System.currentTimeMillis(),
    val canRegenerate: Boolean = true
)

object MonuSmartAssistant {

    fun followUpSuggestions(response: String): List<MonuSuggestion> {
        return listOf(
            MonuSuggestion(
                id = "explain_more",
                label = "Explain more",
                prompt = "Explain this in more detail."
            ),
            MonuSuggestion(
                id = "simple",
                label = "Simplify",
                prompt = "Explain this in simple language."
            ),
            MonuSuggestion(
                id = "examples",
                label = "Examples",
                prompt = "Give practical examples."
            ),
            MonuSuggestion(
                id = "summarize",
                label = "Summarize",
                prompt = "Summarize this briefly."
            )
        )
    }

    fun contextualActions(content: String): List<String> {
        val actions = mutableListOf(
            "Copy",
            "Share",
            "Read aloud"
        )

        if (content.length > 300) {
            actions.add("Summarize")
        }

        if (
            content.contains("```") ||
            content.contains("fun ") ||
            content.contains("class ")
        ) {
            actions.add("Copy code")
        }

        return actions
    }
}
