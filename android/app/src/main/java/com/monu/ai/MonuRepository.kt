package com.monu.ai

import android.content.Context
import kotlinx.coroutines.flow.Flow

class MonuRepository(
    context: Context
) {

    private val dao =
        MonuDatabase.get(context).dao()

    fun conversations():
        Flow<List<ConversationEntity>> =
        dao.conversations()

    fun searchChats(
        query: String
    ): Flow<List<ConversationEntity>> =
        dao.searchConversations(query)

    fun messages(
        conversationId: Long
    ): Flow<List<MessageEntity>> =
        dao.messages(conversationId)

    fun memories():
        Flow<List<MemoryEntity>> =
        dao.memories()

    suspend fun newConversation(
        title: String = "New Chat"
    ): Long {

        return dao.insertConversation(
            ConversationEntity(
                title = title
            )
        )
    }

    suspend fun addMessage(
        conversationId: Long,
        role: String,
        content: String,
        route: String = "local"
    ) {

        dao.insertMessage(
            MessageEntity(
                conversationId = conversationId,
                role = role,
                content = content,
                route = route
            )
        )
    }

    suspend fun renameConversation(
        conversation: ConversationEntity,
        newTitle: String
    ) {

        dao.updateConversation(
            conversation.copy(
                title = newTitle,
                updatedAt = System.currentTimeMillis()
            )
        )
    }

    suspend fun togglePin(
        conversation: ConversationEntity
    ) {

        dao.updateConversation(
            conversation.copy(
                pinned = !conversation.pinned,
                updatedAt = System.currentTimeMillis()
            )
        )
    }

    suspend fun deleteConversation(
        conversation: ConversationEntity
    ) {

        dao.deleteMessages(
            conversation.id
        )

        dao.deleteConversation(
            conversation
        )
    }

    suspend fun remember(
        key: String,
        value: String
    ) {

        dao.insertMemory(
            MemoryEntity(
                key = key,
                value = value
            )
        )
    }
}
