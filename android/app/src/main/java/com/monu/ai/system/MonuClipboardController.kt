package com.monu.ai.system

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context

object MonuClipboardController {

    fun copy(
        context: Context,
        label: String,
        text: String
    ) {

        val clipboard =
            context.getSystemService(
                Context.CLIPBOARD_SERVICE
            ) as ClipboardManager

        clipboard.setPrimaryClip(
            ClipData.newPlainText(
                label,
                text
            )
        )
    }

    fun read(
        context: Context
    ): String? {

        val clipboard =
            context.getSystemService(
                Context.CLIPBOARD_SERVICE
            ) as ClipboardManager

        val clip =
            clipboard.primaryClip
                ?: return null

        if (clip.itemCount == 0) {
            return null
        }

        return clip
            .getItemAt(0)
            .coerceToText(context)
            ?.toString()
    }
}
