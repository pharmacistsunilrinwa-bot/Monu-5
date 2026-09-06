package com.monu.ai.production

object MonuProductionFeatures {

    val core = listOf(
        "Central AI Runtime",
        "Gemini Direct Provider",
        "Server Optional Routing",
        "Conversation Memory",
        "Streaming Responses",
        "Response Regeneration",
        "Edit And Resend",
        "Response Variants"
    )

    val interaction = listOf(
        "Voice Input",
        "Voice Output",
        "Copy Response",
        "Share Response",
        "Multimodal Composer",
        "Attachments",
        "Android Share Receiver",
        "Clipboard Intelligence"
    )

    val intelligence = listOf(
        "Central AI Brain",
        "Memory Context",
        "Execution Planning",
        "Route Aggregation",
        "Smart Suggestions",
        "Context Actions",
        "Command History"
    )

    val storage = listOf(
        "Permanent Conversations",
        "Pinned Chats",
        "Chat Search",
        "Favorites",
        "Notebooks",
        "Media Library",
        "Backup",
        "Import Export",
        "Offline Queue"
    )

    val android = listOf(
        "Deep Links",
        "App Shortcuts",
        "Dynamic Theme",
        "Material You",
        "Tablet Layout",
        "Foldable Layout",
        "Home Widget Architecture",
        "Notifications",
        "Foreground Runtime"
    )

    val security = listOf(
        "App Lock Architecture",
        "Biometric Architecture",
        "Privacy Modes",
        "Sensitive Data Policy",
        "Screen Privacy",
        "Permission Policy",
        "Secure Build Configuration"
    )

    fun totalFeatureCount(): Int {
        return core.size +
            interaction.size +
            intelligence.size +
            storage.size +
            android.size +
            security.size
    }
}
