package com.monu.ai.integrity

object MonuFeatureIntegrity {

    val criticalFeatures = listOf(
        "chat",
        "voice",
        "memory",
        "media",
        "network",
        "gemini",
        "navigation",
        "permissions",
        "health",
        "security",
        "offline_sync",
        "runtime"
    )

    fun check(
        registeredFeatures: Collection<String>
    ): FeatureIntegrityReport {

        val missing = criticalFeatures.filterNot {
            feature ->
            registeredFeatures.any {
                it.equals(feature, ignoreCase = true)
            }
        }

        return FeatureIntegrityReport(
            complete = missing.isEmpty(),
            missing = missing,
            totalRequired = criticalFeatures.size,
            available = criticalFeatures.size - missing.size
        )
    }
}

data class FeatureIntegrityReport(
    val complete: Boolean,
    val missing: List<String>,
    val totalRequired: Int,
    val available: Int
)
