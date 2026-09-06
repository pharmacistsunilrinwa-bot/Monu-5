package com.monu.ai.system

import android.content.Context

class MonuSystemIntegrationController(
    private val context: Context
) {

    fun initialize() {
        MonuNotificationController.initialize(
            context
        )
    }

    fun share(text: String) {
        MonuAndroidIntents.shareText(
            context,
            text
        )
    }

    fun openUrl(url: String) {
        MonuAndroidIntents.openUrl(
            context,
            url
        )
    }

    fun copy(text: String) {
        MonuClipboardController.copy(
            context,
            "MONU",
            text
        )
    }

    fun notify(
        title: String,
        message: String
    ) {
        MonuNotificationController.show(
            context,
            title,
            message
        )
    }
}
