package com.monu.ai.integration

import android.content.Context

/**
 * Converts recognized voice text into
 * normal MONU chat commands.
 */
class MonuVoiceIntegration(
    private val context: Context
) {

    private val chatIntegration = MonuChatIntegration(context)

    fun processVoiceText(
        recognizedText: String
    ): MonuChatIntegration.ChatResult {

        return chatIntegration.send(
            recognizedText.trim()
        )
    }
}
