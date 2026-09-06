package com.monu.ai.system

enum class MonuSystemAction {
    OPEN_BROWSER,
    SHARE_TEXT,
    OPEN_APP_SETTINGS,
    OPEN_NETWORK_SETTINGS,
    COPY_TO_CLIPBOARD,
    SHOW_NOTIFICATION
}

data class MonuSystemActionRequest(
    val action: MonuSystemAction,
    val payload: String? = null
)

object MonuSystemActionRegistry {

    fun supported(): List<MonuSystemAction> {
        return MonuSystemAction.entries
    }

    fun requiresUserInteraction(
        action: MonuSystemAction
    ): Boolean {

        return when (action) {
            MonuSystemAction.OPEN_BROWSER -> true
            MonuSystemAction.SHARE_TEXT -> true
            MonuSystemAction.OPEN_APP_SETTINGS -> true
            MonuSystemAction.OPEN_NETWORK_SETTINGS -> true
            else -> false
        }
    }
}
