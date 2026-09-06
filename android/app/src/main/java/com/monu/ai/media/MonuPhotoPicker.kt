package com.monu.ai.media

import android.content.Intent
import android.os.Build
import android.provider.MediaStore

object MonuPhotoPicker {

    fun createIntent(): Intent {

        return if (
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
        ) {
            Intent(
                MediaStore.ACTION_PICK_IMAGES
            )
        } else {
            Intent(
                Intent.ACTION_OPEN_DOCUMENT
            ).apply {
                addCategory(
                    Intent.CATEGORY_OPENABLE
                )
                type = "image/*"
            }
        }
    }
}
