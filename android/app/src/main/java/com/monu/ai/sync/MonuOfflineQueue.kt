package com.monu.ai.sync

import java.util.concurrent.ConcurrentHashMap

object MonuOfflineQueue {

    private val requests =
        ConcurrentHashMap<String, MonuQueuedRequest>()

    fun enqueue(request: MonuQueuedRequest) {
        requests[request.id] = request
    }

    fun pending(): List<MonuQueuedRequest> {
        return requests.values
            .filter {
                it.status == MonuSyncStatus.PENDING ||
                it.status == MonuSyncStatus.FAILED
            }
            .sortedBy { it.createdAt }
    }

    fun update(request: MonuQueuedRequest) {
        requests[request.id] = request
    }

    fun remove(id: String) {
        requests.remove(id)
    }

    fun clearCompleted() {
        requests.entries.removeIf {
            it.value.status == MonuSyncStatus.COMPLETED
        }
    }

    fun size(): Int = requests.size
}
