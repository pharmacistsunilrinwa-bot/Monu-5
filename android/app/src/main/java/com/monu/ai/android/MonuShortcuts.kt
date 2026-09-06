package com.monu.ai.android

data class MonuShortcut(
    val id: String,
    val label: String,
    val destination: String
)

object MonuShortcuts {

    val shortcuts = listOf(
        MonuShortcut(
            id = "new_chat",
            label = "New Chat",
            destination = "new_chat"
        ),
        MonuShortcut(
            id = "voice_chat",
            label = "Voice Conversation",
            destination = "voice"
        ),
        MonuShortcut(
            id = "open_camera",
            label = "Camera",
            destination = "camera"
        ),
        MonuShortcut(
            id = "connections",
            label = "Connection Status",
            destination = "connections"
        )
    )

    fun find(id: String): MonuShortcut? {
        return shortcuts.firstOrNull { it.id == id }
    }
}
