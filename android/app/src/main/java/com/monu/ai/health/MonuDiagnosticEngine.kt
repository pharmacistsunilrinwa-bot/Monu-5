package com.monu.ai.health

import com.monu.ai.network.ConnectionState

data class MonuDiagnosticReport(
    val healthy: Boolean,
    val summary: String,
    val details: List<String>,
    val timestamp: Long =
        System.currentTimeMillis()
)

class MonuDiagnosticEngine {

    fun analyze(
        connectionState: ConnectionState?,
        lastSuccessfulCheck: Long?
    ): MonuDiagnosticReport {

        val details =
            mutableListOf<String>()

        var healthy = true

        if (connectionState == null) {

            healthy = false

            details.add(
                "Connection state is unavailable"
            )

        } else {

            details.add(
                "Connection state: $connectionState"
            )
        }

        val now =
            System.currentTimeMillis()

        if (lastSuccessfulCheck == null) {

            healthy = false

            details.add(
                "No successful health check recorded"
            )

        } else {

            val elapsed =
                now - lastSuccessfulCheck

            if (
                elapsed >
                5 * 60 * 1000
            ) {

                healthy = false

                details.add(
                    "Last successful check exceeded five minutes"
                )
            }
        }

        return MonuDiagnosticReport(
            healthy = healthy,
            summary =
                if (healthy)
                    "MONU system is healthy"
                else
                    "MONU requires connection diagnostics",
            details = details
        )
    }
}
