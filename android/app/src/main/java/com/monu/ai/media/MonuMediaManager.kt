package com.monu.ai.media

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns

class MonuMediaManager(
    private val context: Context
) {

    fun inspect(
        uri: Uri
    ): MonuMediaItem {

        val mimeType =
            context.contentResolver.getType(uri)

        var name: String? = null
        var size: Long? = null

        context.contentResolver.query(
            uri,
            null,
            null,
            null,
            null
        )?.use { cursor ->

            val nameIndex =
                cursor.getColumnIndex(
                    OpenableColumns.DISPLAY_NAME
                )

            val sizeIndex =
                cursor.getColumnIndex(
                    OpenableColumns.SIZE
                )

            if (cursor.moveToFirst()) {

                if (nameIndex >= 0) {
                    name =
                        cursor.getString(nameIndex)
                }

                if (sizeIndex >= 0) {
                    size =
                        cursor.getLong(sizeIndex)
                }
            }
        }

        return MonuMediaItem(
            uri = uri,
            type =
                MonuMediaClassifier.classify(
                    mimeType
                ),
            displayName = name,
            mimeType = mimeType,
            sizeBytes = size
        )
    }

    fun readBytes(
        uri: Uri
    ): ByteArray {
        return context.contentResolver
            .openInputStream(uri)
            ?.use {
                it.readBytes()
            }
            ?: ByteArray(0)
    }
}
