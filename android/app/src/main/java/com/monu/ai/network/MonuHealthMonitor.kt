package com.monu.ai.network

import android.content.Context
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

data class MonuHealthReport(
    val apkToServer: ConnectionResult,
    val lastCheck: Long,
    val diagnostic: String
)

class MonuHealthMonitor(
    context: Context
) {

    private val network =
        MonuNetworkEngine(context)

    suspend fun checkNow(): MonuHealthReport {

        val result =
            network.checkServerConnection()

        val diagnostic =
            when {

                com.monu.ai.BuildConfig.MONU_SERVER_URL.isBlank() ->
                    "MONU Server URL is not configured."

                !result.connected ->
                    "Connection failed: ${result.message}"

                else ->
                    "MONU connection healthy. Latency: ${result.latencyMs} ms"
            }

        return MonuHealthReport(
            apkToServer = result,
            lastCheck =
                System.currentTimeMillis(),
            diagnostic = diagnostic
        )
    }

    fun startFiveMinuteMonitor(
        scope: CoroutineScope,
        onReport: (
            MonuHealthReport
        ) -> Unit
    ) {

        scope.launch {

            while (isActive) {

                onReport(
                    checkNow()
                )

                delay(
                    5 * 60 * 1000L
                )
            }
        }
    }
}
