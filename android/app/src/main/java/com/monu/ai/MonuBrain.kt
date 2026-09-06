package com.monu.ai

enum class MonuRoute {
    SERVER,
    LOCAL_MEMORY,
    GEMINI,
    WIKIPEDIA
}

enum class MonuAction {
    CHAT,
    SEARCH,
    REMEMBER,
    IMAGE,
    VIDEO,
    MUSIC,
    CANVAS,
    LEARNING,
    PERSONAL_INTELLIGENCE,
    FILE,
    CONNECTION,
    SETTINGS,
    UNKNOWN
}

data class BrainDecision(
    val action: MonuAction,
    val routes: List<MonuRoute>,
    val requiresPermission: String? = null
)

class MonuBrain {

    val identity =
        "You are MONU AI, a personal AI assistant for Sunil Rinwa."

    fun decide(input: String): BrainDecision {

        val text = input.trim().lowercase()

        val action = when {

            "remember" in text ||
            "याद रख" in text ->
                MonuAction.REMEMBER

            "search" in text ||
            "खोज" in text ||
            "विकिपीडिया" in text ->
                MonuAction.SEARCH

            "image" in text ||
            "photo" in text ||
            "तस्वीर" in text ->
                MonuAction.IMAGE

            "video" in text ->
                MonuAction.VIDEO

            "music" in text ||
            "गीत" in text ->
                MonuAction.MUSIC

            "canvas" in text ->
                MonuAction.CANVAS

            "learn" in text ||
            "सीख" in text ->
                MonuAction.LEARNING

            else ->
                MonuAction.CHAT
        }

        val routes = when (action) {

            MonuAction.SEARCH -> listOf(
                MonuRoute.SERVER,
                MonuRoute.LOCAL_MEMORY,
                MonuRoute.GEMINI,
                MonuRoute.WIKIPEDIA
            )

            else -> listOf(
                MonuRoute.SERVER,
                MonuRoute.LOCAL_MEMORY,
                MonuRoute.GEMINI
            )
        }

        return BrainDecision(
            action = action,
            routes = routes
        )
    }
}
