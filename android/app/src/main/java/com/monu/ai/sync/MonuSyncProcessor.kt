package com.monu.ai.sync

interface MonuSyncProcessor {

    suspend fun process(
        request: MonuQueuedRequest
    ): MonuSyncResult
}
