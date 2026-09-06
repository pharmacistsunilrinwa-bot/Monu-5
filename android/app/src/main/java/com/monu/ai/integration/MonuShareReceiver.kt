package com.monu.ai.integration

import android.content.Intent
import android.net.Uri

sealed class MonuSharedContent {

    data class Text(
        val value: String
    ) : MonuSharedContent()

    data class File(
        val uri: Uri,
        val mimeType: String?
    ) : MonuSharedContent()

    data class Files(
        val uris: List<Uri>,
        val mimeType: String?
    ) : MonuSharedContent()
}

object MonuShareReceiver {

    fun parse(
        intent: Intent?
    ): MonuSharedContent? {

        if (intent == null) {
            return null
        }

        return when (
            intent.action
        ) {

            Intent.ACTION_SEND -> {

                val text =
                    intent.getStringExtra(
                        Intent.EXTRA_TEXT
                    )

                if (!text.isNullOrBlank()) {
                    MonuSharedContent.Text(
                        text
                    )
                } else {

                    val uri =
                        intent.getParcelableExtra<Uri>(
                            Intent.EXTRA_STREAM
                        )

                    uri?.let {
                        MonuSharedContent.File(
                            it,
                            intent.type
                        )
                    }
                }
            }

            Intent.ACTION_SEND_MULTIPLE -> {

                val uris =
                    intent.getParcelableArrayListExtra<Uri>(
                        Intent.EXTRA_STREAM
                    ) ?: emptyList()

                if (uris.isEmpty()) {
                    null
                } else {
                    MonuSharedContent.Files(
                        uris,
                        intent.type
                    )
                }
            }

            else -> null
        }
    }
}
