package com.monu.ai.intelligence

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context

data class MonuClipboardContent(
    val text: String,
    val available: Boolean
)

class MonuClipboard(
    private val context: Context
) {

    fun read(): MonuClipboardContent {
        val clipboard =
            context.getSystemService(Context.CLIPBOARD_SERVICE)
                    as ClipboardManager

        if (!clipboard.hasPrimaryClip()) {
            return MonuClipboardContent("", false)
        }

        val item = clipboard.primaryClip
            ?.getItemAt(0)
            ?.coerceToText(context)
            ?.toString()
            ?: ""

        return MonuClipboardContent(
            text = item,
            available = item.isNotBlank()
        )
    }

    fun copy(text: String) {
        val clipboard =
            context.getSystemService(Context.CLIPBOARD_SERVICE)
                    as ClipboardManager

        clipboard.setPrimaryClip(
            ClipData.newPlainText("MONU", text)
        )
    }
}
