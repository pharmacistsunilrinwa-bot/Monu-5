package com.monu.ai.media

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore

object MonuCameraManager {

    fun createCameraIntent(
        outputUri: Uri
    ): Intent {

        return Intent(
            MediaStore.ACTION_IMAGE_CAPTURE
        ).apply {

            putExtra(
                MediaStore.EXTRA_OUTPUT,
                outputUri
            )

            addFlags(
                Intent.FLAG_GRANT_WRITE_URI_PERMISSION
            )

            addFlags(
                Intent.FLAG_GRANT_READ_URI_PERMISSION
            )
        }
    }

    fun createVideoIntent(): Intent {
        return Intent(
            MediaStore.ACTION_VIDEO_CAPTURE
        )
    }
}
