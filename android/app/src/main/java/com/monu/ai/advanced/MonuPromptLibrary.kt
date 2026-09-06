package com.monu.ai.advanced

data class MonuPromptTemplate(
    val id: String,
    val title: String,
    val prompt: String
)

object MonuPromptLibrary {

    val templates = mutableListOf(

        MonuPromptTemplate(
            id = "summarize",
            title = "Summarize",
            prompt =
                "Summarize the following content clearly:"
        ),

        MonuPromptTemplate(
            id = "explain",
            title = "Explain",
            prompt =
                "Explain this step by step in simple language:"
        ),

        MonuPromptTemplate(
            id = "improve",
            title = "Improve Writing",
            prompt =
                "Improve the following text while preserving its meaning:"
        ),

        MonuPromptTemplate(
            id = "analyze",
            title = "Analyze",
            prompt =
                "Analyze the following information and provide key insights:"
        ),

        MonuPromptTemplate(
            id = "plan",
            title = "Create Plan",
            prompt =
                "Create a practical step-by-step plan for:"
        )
    )

    fun add(
        template: MonuPromptTemplate
    ) {
        templates.add(template)
    }

    fun find(
        id: String
    ): MonuPromptTemplate? {

        return templates.firstOrNull {
            it.id == id
        }
    }
}
