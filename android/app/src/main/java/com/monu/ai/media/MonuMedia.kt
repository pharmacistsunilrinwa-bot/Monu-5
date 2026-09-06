package com.monu.ai.media

import android.net.Uri

enum class MonuMediaType {
    IMAGE,
    VIDEO,
    AUDIO,
    DOCUMENT,
    UNKNOWN
}

data class MonuMediaItem(
    val uri: Uri,
    val type: MonuMediaType,
    val displayName: String? = null,
    val mimeType: String? = null,
    val sizeBytes: Long? = null
)

object MonuMediaClassifier {

    fun classify(
        mimeType: String?
    ): MonuMediaType {
        return when {
            mimeType?.startsWith("image/") == true ->
                MonuMediaType.IMAGE

            mimeType?.startsWith("video/") == true ->
                MonuMediaType.VIDEO

            mimeType?.startsWith("audio/") == true ->
                MonuMediaType.AUDIO

            mimeType == "application/pdf" ->
                MonuMediaType.DOCUMENT

            else ->
                MonuMediaType.UNKNOWN
        }
    }
}
