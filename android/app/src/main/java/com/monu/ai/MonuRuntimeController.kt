package com.monu.ai

import android.util.Log

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
        } catch (e: Exception) {
            Log.e("MonuRuntimeController", "startCoreServices failed", e)
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
        } catch (e: Exception) {
            Log.e("MonuRuntimeController", "stopCoreServices failed", e)
        }
    }
}
