package com.monu.ai.workspace

data class MonuComposedMessage(
    val text: String,
    val attachments: List<MonuAttachment>,
    val timestamp: Long = System.currentTimeMillis()
)

class MonuMessageComposer(
    private val workspace: MonuAttachmentWorkspace
) {

    fun compose(text: String): MonuComposedMessage {
        return MonuComposedMessage(
            text = text.trim(),
            attachments = workspace.all()
        )
    }

    fun canSend(text: String): Boolean {
        return text.isNotBlank() || workspace.hasAttachments()
    }

    fun reset() {
        workspace.clear()
    }
}
