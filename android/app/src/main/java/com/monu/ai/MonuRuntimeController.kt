package com.monu.ai

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import com.monu.ai.services.MonuForegroundService

object MonuRuntimeController {

    fun startCoreServices(
        context: Context
    ) {
        try {
            val intent = Intent(
                context,
                MonuForegroundService::class.java
            ).apply {
                action =
                    MonuForegroundService.ACTION_START
            }

            ContextCompat.startForegroundService(
                context,
                intent
            )
        } catch (_: Exception) {
        }
    }

    fun stopCoreServices(
        context: Context
    ) {
        try {
            val intent = Intent(
                context,
                MonuForegroundService::class.java
            ).apply {
                action =
                    MonuForegroundService.ACTION_STOP
            }

            context.startService(intent)
        } catch (_: Exception) {
        }
    }
}
