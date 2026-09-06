package com.monu.ai

import android.app.Application
import com.monu.ai.health.MonuHealthScheduler
import com.monu.ai.system.MonuCrashHandler

class MonuApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        MonuCrashHandler.install(this)

        try {
            MonuHealthScheduler.schedule(this)
        } catch (_: Exception) {
        }
    }
}
