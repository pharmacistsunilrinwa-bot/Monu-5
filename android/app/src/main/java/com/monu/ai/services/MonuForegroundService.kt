package com.monu.ai.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder

class MonuForegroundService : Service() {

    companion object {
        const val CHANNEL_ID = "monu_ai_service"
        const val NOTIFICATION_ID = 7001

        const val ACTION_START =
            "com.monu.ai.START_SERVICE"

        const val ACTION_STOP =
            "com.monu.ai.STOP_SERVICE"
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    override fun onStartCommand(
        intent: Intent?,
        flags: Int,
        startId: Int
    ): Int {

        when (intent?.action) {

            ACTION_STOP -> {
                stopForeground(STOP_FOREGROUND_REMOVE)
                stopSelf()
                return START_NOT_STICKY
            }

            else -> {
                startForeground(
                    NOTIFICATION_ID,
                    buildNotification()
                )
            }
        }

        return START_STICKY
    }

    private fun createNotificationChannel() {

        if (
            Build.VERSION.SDK_INT >=
            Build.VERSION_CODES.O
        ) {

            val channel =
                NotificationChannel(
                    CHANNEL_ID,
                    "MONU AI Service",
                    NotificationManager.IMPORTANCE_LOW
                )

            getSystemService(
                NotificationManager::class.java
            ).createNotificationChannel(channel)
        }
    }

    private fun buildNotification(): Notification {

        return Notification.Builder(
            this,
            CHANNEL_ID
        )
            .setContentTitle("MONU AI")
            .setContentText(
                "MONU background service is active"
            )
            .setSmallIcon(
                android.R.drawable.ic_dialog_info
            )
            .setOngoing(true)
            .build()
    }

    override fun onBind(
        intent: Intent?
    ): IBinder? = null
}
