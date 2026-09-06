package com.monu.ai.actions

import android.content.Context
import android.content.Intent
import android.speech.tts.TextToSpeech
import androidx.core.content.ContextCompat
import java.util.Locale

class MonuResponseActions(
    private val context: Context
) {

    private var textToSpeech: TextToSpeech? = null

    fun copy(
        text: String
    ) {
        val clipboard =
            ContextCompat.getSystemService(
                context,
                android.content.ClipboardManager::class.java
            )

        clipboard?.setPrimaryClip(
            android.content.ClipData.newPlainText(
                "MONU Response",
                text
            )
        )
    }

    fun speak(
        text: String
    ) {
        if (textToSpeech == null) {

            textToSpeech =
                TextToSpeech(context) {
                    if (it == TextToSpeech.SUCCESS) {
                        textToSpeech?.language =
                            Locale.getDefault()

                        textToSpeech?.speak(
                            text,
                            TextToSpeech.QUEUE_FLUSH,
                            null,
                            "monu_response"
                        )
                    }
                }

        } else {

            textToSpeech?.speak(
                text,
                TextToSpeech.QUEUE_FLUSH,
                null,
                "monu_response"
            )
        }
    }

    fun share(
        text: String
    ) {
        val intent =
            Intent(Intent.ACTION_SEND)

        intent.type = "text/plain"

        intent.putExtra(
            Intent.EXTRA_TEXT,
            text
        )

        intent.addFlags(
            Intent.FLAG_ACTIVITY_NEW_TASK
        )

        context.startActivity(
            Intent.createChooser(
                intent,
                "Share MONU response"
            ).addFlags(
                Intent.FLAG_ACTIVITY_NEW_TASK
            )
        )
    }

    fun release() {
        textToSpeech?.stop()
        textToSpeech?.shutdown()
        textToSpeech = null
    }
}
