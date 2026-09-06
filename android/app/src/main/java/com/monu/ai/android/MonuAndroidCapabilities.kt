package com.monu.ai.android

data class MonuAndroidCapability(
    val id: String,
    val title: String,
    val enabled: Boolean = true
)

object MonuAndroidCapabilities {

    val capabilities = listOf(
        MonuAndroidCapability(
            "deep_links",
            "Deep Links"
        ),
        MonuAndroidCapability(
            "app_shortcuts",
            "App Shortcuts"
        ),
        MonuAndroidCapability(
            "dynamic_theme",
            "Dynamic Theme"
        ),
        MonuAndroidCapability(
            "edge_to_edge",
            "Edge To Edge UI"
        ),
        MonuAndroidCapability(
            "responsive_layout",
            "Tablet/Foldable Layout"
        ),
        MonuAndroidCapability(
            "home_widget",
            "Home Screen Widget"
        )
    )

    fun enabled(): List<MonuAndroidCapability> {
        return capabilities.filter { it.enabled }
    }
}
