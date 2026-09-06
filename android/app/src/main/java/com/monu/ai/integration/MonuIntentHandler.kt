package com.monu.ai.integration

import android.content.Intent
import android.net.Uri

data class MonuIncomingContent(
    val text: String? = null,
    val uris: List<Uri> = emptyList(),
    val sourcePackage: String? = null,
    val action: String? = null
)

object MonuIntentHandler {

    fun parse(intent: Intent?): MonuIncomingContent {
        if (intent == null) return MonuIncomingContent()

        val uris = mutableListOf<Uri>()

        when (intent.action) {
            Intent.ACTION_SEND -> {
                val stream = intent.getParcelableExtra<Uri>(Intent.EXTRA_STREAM)
                if (stream != null) uris += stream
            }

            Intent.ACTION_SEND_MULTIPLE -> {
                val streams =
                    intent.getParcelableArrayListExtra<Uri>(Intent.EXTRA_STREAM)

                streams?.let { uris.addAll(it) }
            }
        }

        return MonuIncomingContent(
            text = intent.getStringExtra(Intent.EXTRA_TEXT),
            uris = uris,
            sourcePackage = intent.`package`,
            action = intent.action
        )
    }

    fun hasContent(content: MonuIncomingContent): Boolean {
        return !content.text.isNullOrBlank() || content.uris.isNotEmpty()
    }
}
