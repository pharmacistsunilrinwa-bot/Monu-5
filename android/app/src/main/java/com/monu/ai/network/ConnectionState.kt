package com.monu.ai.network


enum class ConnectionState {
    CONNECTED,
    DISCONNECTED,
    CONNECTING,
    UNKNOWN
}

data class ConnectionDashboardState(
    val apkToServer: String = "Not Configured",
    val serverToApk: String = "Unknown",
    val message: String = "Waiting for connection check",
    val latency: String = "--",
    val lastCheck: String = "--",
    val checking: Boolean = false
)
