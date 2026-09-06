package com.monu.ai.voice

import android.content.Context
import android.speech.tts.TextToSpeech
import java.util.Locale

data class MonuVoiceSettings(
    val pitch: Float = 1.0f,
    val speed: Float = 1.0f,
    val language: Locale = Locale.getDefault()
)

class MonuVoiceController(
    context: Context
) {

    private var ready = false

    private val tts =
        TextToSpeech(context) { status ->
            ready = status == TextToSpeech.SUCCESS
        }

    private var settings =
        MonuVoiceSettings()

    fun update(
        newSettings: MonuVoiceSettings
    ) {
        settings = newSettings

        tts.language =
            settings.language

        tts.setPitch(
            settings.pitch
        )

        tts.setSpeechRate(
            settings.speed
        )
    }

    fun speak(
        text: String
    ) {
        if (!ready) return

        tts.language =
            settings.language

        tts.setPitch(
            settings.pitch
        )

        tts.setSpeechRate(
            settings.speed
        )

        tts.speak(
            text,
            TextToSpeech.QUEUE_FLUSH,
            null,
            "monu_voice"
        )
    }

    fun stop() {
        tts.stop()
    }

    fun release() {
        tts.stop()
        tts.shutdown()
    }
}
