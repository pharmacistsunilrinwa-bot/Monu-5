package com.monu.ai.recovery

data class MonuDraft(
    val conversationId: Long?,
    val content: String,
    val updatedAt: Long = System.currentTimeMillis()
)

data class MonuSessionSnapshot(
    val conversationId: Long?,
    val scrollPosition: Int = 0,
    val selectedModel: String? = null,
    val createdAt: Long = System.currentTimeMillis()
)

object MonuSessionRecovery {

    private var currentDraft: MonuDraft? = null
    private var currentSession: MonuSessionSnapshot? = null

    fun saveDraft(
        conversationId: Long?,
        content: String
    ) {
        currentDraft = MonuDraft(
            conversationId = conversationId,
            content = content
        )
    }

    fun restoreDraft(): MonuDraft? = currentDraft

    fun clearDraft() {
        currentDraft = null
    }

    fun saveSession(snapshot: MonuSessionSnapshot) {
        currentSession = snapshot
    }

    fun restoreSession(): MonuSessionSnapshot? = currentSession

    fun clearSession() {
        currentSession = null
    }
}
