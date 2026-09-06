package com.monu.ai.integration

/**
 * Bridges persistent memory with
 * Central AI Brain context.
 */
class MonuMemoryIntegration {

    data class MemoryContext(
        val conversationId: String?,
        val userContext: List<String>,
        val temporaryContext: List<String>
    )

    fun buildContext(
        conversationId: String?,
        memories: List<String>
    ): MemoryContext {

        return MemoryContext(
            conversationId = conversationId,
            userContext = memories,
            temporaryContext = emptyList()
        )
    }
}
