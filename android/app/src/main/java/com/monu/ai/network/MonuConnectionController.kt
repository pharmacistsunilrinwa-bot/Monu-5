package com.monu.ai.network

import android.content.Context
import com.monu.ai.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MonuConnectionController(
    private val context: Context
) {

    private val network =
        MonuNetworkEngine(context)

    suspend fun check(): ConnectionDashboardState =
        withContext(Dispatchers.IO) {

            val result =
                network.checkServerConnection()

            val formatter =
                SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss",
                    Locale.getDefault()
                )

            ConnectionDashboardState(
                apkToServer =
                    if (
                        BuildConfig.MONU_SERVER_URL.isBlank()
                    )
                        "Not Configured"
                    else if (result.connected)
                        "Connected"
                    else
                        "Disconnected",

                serverToApk =
                    if (result.connected)
                        "Connected"
                    else
                        "Unknown",

                message =
                    result.message,

                latency =
                    result.latencyMs
                        ?.let { "$it ms" }
                        ?: "--",

                lastCheck =
                    formatter.format(
                        Date()
                    ),

                checking = false
            )
        }
}
