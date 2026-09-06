package com.monu.ai.appfeatures

data class MonuMessageRevision(
    val messageId: Long,
    val content: String,
    val timestamp: Long =
        System.currentTimeMillis()
)

class MonuMessageTools {

    private val selectedMessages =
        mutableSetOf<Long>()

    private val revisions =
        mutableMapOf<Long, MutableList<MonuMessageRevision>>()

    fun select(
        messageId: Long
    ) {
        selectedMessages.add(messageId)
    }

    fun deselect(
        messageId: Long
    ) {
        selectedMessages.remove(messageId)
    }

    fun clearSelection() {
        selectedMessages.clear()
    }

    fun selected():
        Set<Long> =
        selectedMessages.toSet()

    fun saveRevision(
        messageId: Long,
        content: String
    ) {
        revisions
            .getOrPut(messageId) {
                mutableListOf()
            }
            .add(
                MonuMessageRevision(
                    messageId,
                    content
                )
            )
    }

    fun history(
        messageId: Long
    ): List<MonuMessageRevision> {

        return revisions[messageId]
            ?.toList()
            ?: emptyList()
    }
}
