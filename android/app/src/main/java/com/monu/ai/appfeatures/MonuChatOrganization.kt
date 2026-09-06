package com.monu.ai.appfeatures

data class MonuChatFolder(
    val id: String,
    val name: String,
    val createdAt: Long =
        System.currentTimeMillis()
)

data class MonuFavoriteItem(
    val id: String,
    val messageId: Long,
    val createdAt: Long =
        System.currentTimeMillis()
)

class MonuChatOrganization {

    private val folders =
        mutableListOf<MonuChatFolder>()

    private val archivedChats =
        mutableSetOf<Long>()

    private val favorites =
        mutableListOf<MonuFavoriteItem>()

    fun createFolder(
        name: String
    ): MonuChatFolder {

        val folder =
            MonuChatFolder(
                id = java.util.UUID
                    .randomUUID()
                    .toString(),
                name = name
            )

        folders.add(folder)

        return folder
    }

    fun archive(
        conversationId: Long
    ) {
        archivedChats.add(conversationId)
    }

    fun restore(
        conversationId: Long
    ) {
        archivedChats.remove(conversationId)
    }

    fun addFavorite(
        messageId: Long
    ) {
        favorites.add(
            MonuFavoriteItem(
                id = java.util.UUID
                    .randomUUID()
                    .toString(),
                messageId = messageId
            )
        )
    }

    fun folders():
        List<MonuChatFolder> =
        folders.toList()

    fun archived():
        Set<Long> =
        archivedChats.toSet()

    fun favorites():
        List<MonuFavoriteItem> =
        favorites.toList()
}
