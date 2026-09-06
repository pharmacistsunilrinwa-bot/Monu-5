package com.monu.ai.network

import com.monu.ai.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class GeminiProvider {

    private val apiKey: String
        get() = BuildConfig.MONU_GEMINI_API_KEY

    suspend fun generate(
        message: String,
        model: String
    ): RouteResult =
        withContext(Dispatchers.IO) {

            if (apiKey.isBlank()) {

                return@withContext RouteResult(
                    route = "gemini",
                    success = false,
                    response =
                        "Gemini API key is not configured in this APK build",
                    latencyMs = null
                )
            }

            val started =
                System.currentTimeMillis()

            try {

                val endpoint =
                    "https://generativelanguage.googleapis.com/" +
                    "v1beta/models/$model:generateContent"

                val connection =
                    URL(endpoint)
                        .openConnection() as HttpURLConnection

                connection.requestMethod = "POST"
                connection.doOutput = true
                connection.connectTimeout = 20_000
                connection.readTimeout = 60_000

                connection.setRequestProperty(
                    "Content-Type",
                    "application/json"
                )

                connection.setRequestProperty(
                    "x-goog-api-key",
                    apiKey
                )

                val parts = JSONArray()
                    .put(
                        JSONObject()
                            .put(
                                "text",
                                message
                            )
                    )

                val contents = JSONArray()
                    .put(
                        JSONObject()
                            .put(
                                "parts",
                                parts
                            )
                    )

                val payload =
                    JSONObject()
                        .put(
                            "contents",
                            contents
                        )
                        .toString()

                connection.outputStream.use {
                    it.write(
                        payload.toByteArray(
                            Charsets.UTF_8
                        )
                    )
                }

                val code =
                    connection.responseCode

                val stream =
                    if (code in 200..299)
                        connection.inputStream
                    else
                        connection.errorStream

                val raw =
                    stream?.bufferedReader()
                        ?.use {
                            it.readText()
                        }
                        ?: ""

                connection.disconnect()

                val response =
                    extractText(raw)

                RouteResult(
                    route = "gemini",
                    success = code in 200..299,
                    response = response,
                    latencyMs =
                        System.currentTimeMillis() - started
                )

            } catch (e: Exception) {

                RouteResult(
                    route = "gemini",
                    success = false,
                    response =
                        e.message
                            ?: "Gemini request failed",
                    latencyMs =
                        System.currentTimeMillis() - started
                )
            }
        }

    private fun extractText(
        raw: String
    ): String {

        return try {

            val root =
                JSONObject(raw)

            val candidates =
                root.optJSONArray(
                    "candidates"
                )

            if (
                candidates != null &&
                candidates.length() > 0
            ) {

                val content =
                    candidates
                        .getJSONObject(0)
                        .optJSONObject(
                            "content"
                        )

                val parts =
                    content?.optJSONArray(
                        "parts"
                    )

                if (
                    parts != null &&
                    parts.length() > 0
                ) {

                    parts
                        .getJSONObject(0)
                        .optString(
                            "text",
                            raw
                        )

                } else raw

            } else {

                val error =
                    root.optJSONObject(
                        "error"
                    )

                error?.optString(
                    "message",
                    raw
                ) ?: raw
            }

        } catch (_: Exception) {

            raw
        }
    }
}
