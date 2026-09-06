package com.monu.ai.integration

data class MonuQuickAction(
    val id: String,
    val title: String,
    val route: String,
    val enabled: Boolean = true
)

object MonuQuickActions {

    val actions = listOf(

        MonuQuickAction(
            id = "new_chat",
            title = "New Chat",
            route = "chat/new"
        ),

        MonuQuickAction(
            id = "voice",
            title = "Voice Conversation",
            route = "voice"
        ),

        MonuQuickAction(
            id = "camera",
            title = "Camera",
            route = "camera"
        ),

        MonuQuickAction(
            id = "notebook",
            title = "Notebook",
            route = "notebooks"
        ),

        MonuQuickAction(
            id = "connection",
            title = "Connection",
            route = "connection"
        )
    )

    fun find(id: String): MonuQuickAction? {
        return actions.firstOrNull { it.id == id }
    }
}
