package com.monu.ai.sync

enum class MonuNetworkState {
    ONLINE,
    OFFLINE,
    LIMITED,
    UNKNOWN
}

data class MonuNetworkSnapshot(
    val state: MonuNetworkState,
    val timestamp: Long = System.currentTimeMillis(),
    val detail: String? = null
)
