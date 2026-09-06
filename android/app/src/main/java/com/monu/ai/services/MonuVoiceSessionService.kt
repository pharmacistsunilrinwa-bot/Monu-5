package com.monu.ai.services

import android.app.Service
import android.content.Intent
import android.os.IBinder

enum class MonuVoiceSessionState {
    STOPPED,
    STARTING,
    LISTENING,
    PROCESSING,
    ERROR
}

class MonuVoiceSessionService : Service() {

    private var state =
        MonuVoiceSessionState.STOPPED

    override fun onStartCommand(
        intent: Intent?,
        flags: Int,
        startId: Int
    ): Int {

        state =
            MonuVoiceSessionState.STARTING

        /*
         * Actual microphone recognition is connected
         * through MonuVoiceEngine after runtime
         * RECORD_AUDIO permission is granted.
         */

        return START_STICKY
    }

    fun currentState():
        MonuVoiceSessionState {
        return state
    }

    override fun onDestroy() {
        state =
            MonuVoiceSessionState.STOPPED
        super.onDestroy()
    }

    override fun onBind(
        intent: Intent?
    ): IBinder? = null
}
