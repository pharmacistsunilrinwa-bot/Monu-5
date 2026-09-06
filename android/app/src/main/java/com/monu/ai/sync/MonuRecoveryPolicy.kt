package com.monu.ai.sync

object MonuRecoveryPolicy {

    const val MAX_ATTEMPTS = 5

    fun nextRetryDelay(attempt: Int): Long {
        return when {
            attempt <= 0 -> 1_000L
            attempt == 1 -> 2_000L
            attempt == 2 -> 5_000L
            attempt == 3 -> 15_000L
            else -> 30_000L
        }
    }

    fun canRetry(attempt: Int): Boolean {
        return attempt < MAX_ATTEMPTS
    }
}
