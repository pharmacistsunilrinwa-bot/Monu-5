package com.monu.ai.advanced

enum class MonuResponseStyle {
    NATURAL,
    CONCISE,
    DETAILED,
    PROFESSIONAL,
    CREATIVE,
    TEACHING
}

data class MonuPersonalityProfile(
    val id: String,
    val name: String,
    val style: MonuResponseStyle,
    val instruction: String
)

object MonuPersonalityRegistry {

    val profiles = listOf(

        MonuPersonalityProfile(
            id = "natural",
            name = "Natural",
            style = MonuResponseStyle.NATURAL,
            instruction =
                "Respond naturally and conversationally."
        ),

        MonuPersonalityProfile(
            id = "concise",
            name = "Concise",
            style = MonuResponseStyle.CONCISE,
            instruction =
                "Give short direct answers."
        ),

        MonuPersonalityProfile(
            id = "detailed",
            name = "Detailed",
            style = MonuResponseStyle.DETAILED,
            instruction =
                "Provide detailed structured explanations."
        ),

        MonuPersonalityProfile(
            id = "professional",
            name = "Professional",
            style = MonuResponseStyle.PROFESSIONAL,
            instruction =
                "Use professional clear language."
        ),

        MonuPersonalityProfile(
            id = "teacher",
            name = "Teacher",
            style = MonuResponseStyle.TEACHING,
            instruction =
                "Explain step by step in an easy way."
        )
    )
}
