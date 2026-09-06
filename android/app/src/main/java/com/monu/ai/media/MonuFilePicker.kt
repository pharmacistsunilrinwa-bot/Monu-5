package com.monu.ai.media

import android.content.Context
import android.content.Intent
import android.net.Uri

object MonuFilePicker {

    fun createIntent(
        allowMultiple: Boolean = false
    ): Intent {
        return Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "*/*"
            putExtra(
                Intent.EXTRA_ALLOW_MULTIPLE,
                allowMultiple
            )
        }
    }

    fun persistPermission(
        context: Context,
        uri: Uri,
        flags: Int
    ) {
        try {
            context.contentResolver.takePersistableUriPermission(
                uri,
                flags and (
                    Intent.FLAG_GRANT_READ_URI_PERMISSION or
                    Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                )
            )
        } catch (_: SecurityException) {
        }
    }
}
