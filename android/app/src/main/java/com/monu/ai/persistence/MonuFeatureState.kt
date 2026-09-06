package com.monu.ai.persistence

class MonuFeatureState(
    private val persistence:
        MonuPersistence
) {

    fun setVoiceEnabled(
        enabled: Boolean
    ) {
        persistence.putBoolean(
            MonuStorageKeys.VOICE_ENABLED,
            enabled
        )
    }

    fun isVoiceEnabled(): Boolean {
        return persistence.getBoolean(
            MonuStorageKeys.VOICE_ENABLED
        )
    }

    fun setPrivacyMode(
        enabled: Boolean
    ) {
        persistence.putBoolean(
            MonuStorageKeys.PRIVACY_MODE,
            enabled
        )
    }

    fun isPrivacyMode(): Boolean {
        return persistence.getBoolean(
            MonuStorageKeys.PRIVACY_MODE
        )
    }

    fun setTemporaryChat(
        enabled: Boolean
    ) {
        persistence.putBoolean(
            MonuStorageKeys.TEMPORARY_CHAT,
            enabled
        )
    }

    fun isTemporaryChat(): Boolean {
        return persistence.getBoolean(
            MonuStorageKeys.TEMPORARY_CHAT
        )
    }
}
