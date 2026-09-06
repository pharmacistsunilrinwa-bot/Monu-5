package com.monu.ai.widget

data class MonuWidgetState(
    val title: String = "MONU AI",
    val subtitle: String = "Ready",
    val quickAction: String = "new_chat",
    val updatedAt: Long = System.currentTimeMillis()
)

enum class MonuWidgetAction {
    OPEN_APP,
    NEW_CHAT,
    VOICE_CHAT,
    CAMERA,
    CONNECTIONS
}

class MonuWidgetController {

    private var state = MonuWidgetState()

    fun current(): MonuWidgetState = state

    fun update(
        title: String,
        subtitle: String,
        action: String
    ) {
        state = MonuWidgetState(
            title = title,
            subtitle = subtitle,
            quickAction = action,
            updatedAt = System.currentTimeMillis()
        )
    }

    fun action(
        action: MonuWidgetAction
    ): String {
        return when (action) {
            MonuWidgetAction.OPEN_APP -> "home"
            MonuWidgetAction.NEW_CHAT -> "new_chat"
            MonuWidgetAction.VOICE_CHAT -> "voice"
            MonuWidgetAction.CAMERA -> "camera"
            MonuWidgetAction.CONNECTIONS -> "connections"
        }
    }
}
