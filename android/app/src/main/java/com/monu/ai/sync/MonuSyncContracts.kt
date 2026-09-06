package com.monu.ai.sync

enum class MonuSyncStatus {
    PENDING,
    PROCESSING,
    COMPLETED,
    FAILED,
    CANCELLED
}

data class MonuQueuedRequest(
    val id: String,
    val conversationId: String?,
    val payload: String,
    val createdAt: Long = System.currentTimeMillis(),
    val attempts: Int = 0,
    val status: MonuSyncStatus = MonuSyncStatus.PENDING
)

data class MonuSyncResult(
    val requestId: String,
    val success: Boolean,
    val message: String? = null
)
