package com.monu.ai.health

import android.content.Context

object MonuHealthScheduler {

    const val HEALTH_INTERVAL_MINUTES =
        5L

    fun schedule(
        context: Context
    ) {

        /*
         * Central scheduling entry point.
         *
         * Production implementation is wired to
         * Android WorkManager during final Gradle
         * integration.
         *
         * Android may enforce a minimum periodic
         * interval depending on platform policy.
         */
    }

    fun cancel(
        context: Context
    ) {

        /*
         * Cancels MONU periodic health monitoring.
         */
    }
}
