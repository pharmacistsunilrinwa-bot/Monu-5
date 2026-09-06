package com.monu.ai.network

data class ConnectionDashboardState(
    val apkToServer: String = "Not Configured",
    val serverToApk: String = "Unknown",
    val message: String = "Waiting for connection check",
    val latency: String = "--",
    val lastCheck: String = "--",
    val checking: Boolean = false
)
