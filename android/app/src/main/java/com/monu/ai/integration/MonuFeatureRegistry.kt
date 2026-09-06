package com.monu.ai.integration

data class MonuFeature(
    val id: String,
    val title: String,
    val route: String,
    val enabled: Boolean = true
)

object MonuFeatureRegistry {

    private val features = listOf(

        MonuFeature(
            "chat",
            "Chat",
            "chat"
        ),

        MonuFeature(
            "dashboard",
            "Dashboard",
            "dashboard"
        ),

        MonuFeature(
            "connection",
            "Connection",
            "connection"
        ),

        MonuFeature(
            "models",
            "AI Models",
            "models"
        ),

        MonuFeature(
            "settings",
            "Settings",
            "settings"
        ),

        MonuFeature(
            "profile",
            "Profile",
            "profile"
        ),

        MonuFeature(
            "notebooks",
            "Notebooks",
            "notebooks"
        ),

        MonuFeature(
            "voice",
            "Voice",
            "voice"
        ),

        MonuFeature(
            "library",
            "Library",
            "library"
        ),

        MonuFeature(
            "search",
            "Search",
            "search"
        ),

        MonuFeature(
            "timeline",
            "Activity Timeline",
            "timeline"
        ),

        MonuFeature(
            "backup",
            "Backup",
            "backup"
        ),

        MonuFeature(
            "privacy",
            "Privacy",
            "privacy"
        )
    )

    fun all(): List<MonuFeature> =
        features

    fun find(
        id: String
    ): MonuFeature? {

        return features.firstOrNull {
            it.id == id
        }
    }
}
