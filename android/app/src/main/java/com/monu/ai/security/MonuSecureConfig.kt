package com.monu.ai.security

import com.monu.ai.BuildConfig

object MonuSecureConfig {

    fun geminiApiKey(): String {

        return try {
            BuildConfig.MONU_GEMINI_API_KEY
                .trim()
        } catch (_: Exception) {
            ""
        }
    }

    fun serverUrl(): String {

        return try {
            BuildConfig.MONU_SERVER_URL
                .trim()
                .trimEnd('/')
        } catch (_: Exception) {
            ""
        }
    }

    fun hasGeminiKey(): Boolean {

        return geminiApiKey().isNotBlank()
    }

    fun hasServerUrl(): Boolean {

        return serverUrl().isNotBlank()
    }
}
