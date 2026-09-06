package com.monu.ai.chat

enum class MonuChatAction {
    COPY,
    SPEAK,
    SHARE,
    REGENERATE,
    STOP,
    RETRY,
    EDIT,
    SELECT_VARIANT,
    SAVE_TO_NOTEBOOK,
    FAVORITE
}

data class MonuChatActionRequest(
    val action: MonuChatAction,
    val messageId: Long,
    val payload: String? = null
)

object MonuChatActionPolicy {

    fun allowed(
        action: MonuChatAction,
        isGenerating: Boolean
    ): Boolean {
        return when (action) {
            MonuChatAction.STOP -> isGenerating

            MonuChatAction.REGENERATE,
            MonuChatAction.RETRY ->
                !isGenerating

            else -> true
        }
    }
}
