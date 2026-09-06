package com.monu.ai.system

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings

object MonuAndroidIntents {

    fun openUrl(
        context: Context,
        url: String
    ) {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(url)
        ).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }

        context.startActivity(intent)
    }

    fun shareText(
        context: Context,
        text: String,
        title: String = "Share with MONU"
    ) {
        val intent = Intent(
            Intent.ACTION_SEND
        ).apply {
            type = "text/plain"
            putExtra(
                Intent.EXTRA_TEXT,
                text
            )
            addFlags(
                Intent.FLAG_ACTIVITY_NEW_TASK
            )
        }

        context.startActivity(
            Intent.createChooser(
                intent,
                title
            ).apply {
                addFlags(
                    Intent.FLAG_ACTIVITY_NEW_TASK
                )
            }
        )
    }

    fun openAppSettings(
        context: Context
    ) {
        val intent = Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        ).apply {
            data = Uri.parse(
                "package:${context.packageName}"
            )
            addFlags(
                Intent.FLAG_ACTIVITY_NEW_TASK
            )
        }

        context.startActivity(intent)
    }

    fun openWirelessSettings(
        context: Context
    ) {
        val intent = Intent(
            Settings.ACTION_WIRELESS_SETTINGS
        ).apply {
            addFlags(
                Intent.FLAG_ACTIVITY_NEW_TASK
            )
        }

        context.startActivity(intent)
    }
}
