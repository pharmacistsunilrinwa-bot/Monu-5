package com.monu.ai.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.monu.ai.BrainDecision
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

data class ConnectionResult(
    val connected: Boolean,
    val latencyMs: Long?,
    val message: String,
    val timestamp: Long = System.currentTimeMillis()
)

data class RouteResult(
    val route: String,
    val success: Boolean,
    val response: String,
    val latencyMs: Long?
)

data class QuadRouteResult(
    val results: List<RouteResult>
)

object MonuNetworkConfig {
    const val DEFAULT_TIMEOUT_MS = 15_000
}

class MonuNetworkEngine(
    private val context: Context
) {

    fun hasInternet(): Boolean {
        val manager = context.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager

        val network = manager.activeNetwork ?: return false

        val capabilities =
            manager.getNetworkCapabilities(network)
                ?: return false

        return capabilities.hasCapability(
            NetworkCapabilities.NET_CAPABILITY_INTERNET
        )
    }

    suspend fun checkServerConnection(): ConnectionResult =
        withContext(Dispatchers.IO) {

            if (com.monu.ai.BuildConfig.MONU_SERVER_URL.isBlank()) {
                return@withContext ConnectionResult(
                    connected = false,
                    latencyMs = null,
                    message = "Not Configured"
                )
            }

            val started = System.currentTimeMillis()

            try {
                val connection =
                    URL(
                        com.monu.ai.BuildConfig.MONU_SERVER_URL
                            .trimEnd('/') + "/health"
                    )
                        .openConnection() as HttpURLConnection

                connection.connectTimeout = 10_000
                connection.readTimeout = 10_000
                connection.requestMethod = "GET"

                val code = connection.responseCode

                connection.disconnect()

                ConnectionResult(
                    connected = code in 200..299,
                    latencyMs =
                        System.currentTimeMillis() - started,
                    message = "HTTP $code"
                )

            } catch (e: Exception) {

                ConnectionResult(
                    connected = false,
                    latencyMs =
                        System.currentTimeMillis() - started,
                    message =
                        e.message ?: "Connection failed"
                )
            }
        }

    suspend fun wikipedia(
        query: String
    ): RouteResult =
        withContext(Dispatchers.IO) {

            val started = System.currentTimeMillis()

            try {

                val encoded =
                    URLEncoder.encode(
                        query,
                        "UTF-8"
                    )

                val url =
                    URL(
                        "https://en.wikipedia.org/api/rest_v1/page/summary/$encoded"
                    )

                val connection =
                    url.openConnection() as HttpURLConnection

                connection.connectTimeout = 12_000
                connection.readTimeout = 12_000
                connection.requestMethod = "GET"

                val code =
                    connection.responseCode

                val stream =
                    if (code in 200..299)
                        connection.inputStream
                    else
                        connection.errorStream

                val body =
                    stream?.bufferedReader()
                        ?.use { it.readText() }
                        ?: ""

                connection.disconnect()

                val response =
                    try {
                        JSONObject(body)
                            .optString(
                                "extract",
                                body
                            )
                    } catch (_: Exception) {
                        body
                    }

                RouteResult(
                    route = "wikipedia",
                    success = code in 200..299,
                    response = response,
                    latencyMs =
                        System.currentTimeMillis() - started
                )

            } catch (e: Exception) {

                RouteResult(
                    route = "wikipedia",
                    success = false,
                    response =
                        e.message ?: "Wikipedia failed",
                    latencyMs =
                        System.currentTimeMillis() - started
                )
            }
        }

    suspend fun monuServer(
        message: String
    ): RouteResult =
        withContext(Dispatchers.IO) {

            if (com.monu.ai.BuildConfig.MONU_SERVER_URL.isBlank()) {

                return@withContext RouteResult(
                    route = "monu_server",
                    success = false,
                    response = "Server not configured",
                    latencyMs = null
                )
            }

            val started =
                System.currentTimeMillis()

            try {

                val connection =
                    URL(
                        com.monu.ai.BuildConfig.MONU_SERVER_URL
                            .trimEnd('/') + "/chat"
                    )
                        .openConnection() as HttpURLConnection

                connection.requestMethod = "POST"
                connection.doOutput = true
                connection.connectTimeout = 15_000
                connection.readTimeout = 30_000
                connection.setRequestProperty(
                    "Content-Type",
                    "application/json"
                )

                val payload =
                    JSONObject()
                        .put("message", message)
                        .toString()

                connection.outputStream.use {
                    it.write(
                        payload.toByteArray()
                    )
                }

                val code =
                    connection.responseCode

                val stream =
                    if (code in 200..299)
                        connection.inputStream
                    else
                        connection.errorStream

                val response =
                    stream?.bufferedReader()
                        ?.use { it.readText() }
                        ?: ""

                connection.disconnect()

                RouteResult(
                    route = "monu_server",
                    success = code in 200..299,
                    response = response,
                    latencyMs =
                        System.currentTimeMillis() - started
                )

            } catch (e: Exception) {

                RouteResult(
                    route = "monu_server",
                    success = false,
                    response =
                        e.message ?: "Server request failed",
                    latencyMs =
                        System.currentTimeMillis() - started
                )
            }
        }
}
