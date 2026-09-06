package com.monu.ai.system

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat

object MonuNotificationController {

    const val CHANNEL_ID =
        "monu_ai_channel"

    fun initialize(
        context: Context
    ) {

        if (
            Build.VERSION.SDK_INT >=
            Build.VERSION_CODES.O
        ) {

            val channel =
                NotificationChannel(
                    CHANNEL_ID,
                    "MONU AI",
                    NotificationManager.IMPORTANCE_DEFAULT
                )

            val manager =
                context.getSystemService(
                    NotificationManager::class.java
                )

            manager.createNotificationChannel(
                channel
            )
        }
    }

    fun show(
        context: Context,
        title: String,
        message: String
    ) {

        if (
            Build.VERSION.SDK_INT >= 33 &&
            ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        val notification =
            NotificationCompat.Builder(
                context,
                CHANNEL_ID
            )
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(
                    android.R.drawable.ic_dialog_info
                )
                .setAutoCancel(true)
                .build()

        val manager =
            context.getSystemService(
                Context.NOTIFICATION_SERVICE
            ) as NotificationManager

        manager.notify(
            System.currentTimeMillis()
                .toInt(),
            notification
        )
    }
}
