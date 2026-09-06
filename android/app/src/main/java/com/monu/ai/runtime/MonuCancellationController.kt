package com.monu.ai.runtime

import java.util.concurrent.ConcurrentHashMap

object MonuCancellationController {

    private val cancelled =
        ConcurrentHashMap<String, Boolean>()

    fun cancel(requestId: String) {
        cancelled[requestId] = true
    }

    fun isCancelled(requestId: String): Boolean {
        return cancelled[requestId] == true
    }

    fun clear(requestId: String) {
        cancelled.remove(requestId)
    }
}
