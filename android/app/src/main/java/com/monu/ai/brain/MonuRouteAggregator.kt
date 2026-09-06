package com.monu.ai.brain

class MonuRouteAggregator {

    fun aggregate(
        results: List<MonuRouteResult>
    ): MonuRouteResult? {

        if (results.isEmpty()) {
            return null
        }

        return results
            .filter {
                it.success &&
                it.content.isNotBlank()
            }
            .minByOrNull {
                it.latencyMs
            }
            ?: results.first()
    }

    fun diagnostics(
        results: List<MonuRouteResult>
    ): Map<String, String> {

        return results.associate {
            it.route to
                if (it.success)
                    "CONNECTED ${it.latencyMs}ms"
                else
                    "FAILED"
        }
    }
}
