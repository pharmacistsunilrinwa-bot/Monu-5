package com.monu.ai.brain

import kotlinx.coroutines.flow.first

import com.monu.ai.MonuRepository

class MonuMemoryContext(
    private val repository: MonuRepository
) {

    suspend fun buildContext(
        conversationId: Long?
    ): String {

        if (conversationId == null) {
            return ""
        }

        return try {

            val messages =
                repository.messages(
                    conversationId
                ).first()

            messages
                .takeLast(20)
                .joinToString(
                    separator = "\n"
                ) {
                    "${it.role}: ${it.content}"
                }

        } catch (_: Exception) {
            ""
        }
    }
}
