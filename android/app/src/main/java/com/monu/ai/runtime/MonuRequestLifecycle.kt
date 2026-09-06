package com.monu.ai.runtime

enum class MonuRequestStage {
    CREATED,
    MEMORY_ANALYSIS,
    ROUTING,
    PROVIDER_SELECTION,
    GENERATING,
    STREAMING,
    COMPLETED,
    FAILED,
    CANCELLED
}

data class MonuRequestLifecycle(
    val requestId: String,
    var stage: MonuRequestStage = MonuRequestStage.CREATED,
    var error: String? = null
) {
    fun moveTo(next: MonuRequestStage) {
        stage = next
    }

    fun fail(message: String) {
        error = message
        stage = MonuRequestStage.FAILED
    }

    fun cancel() {
        stage = MonuRequestStage.CANCELLED
    }
}
