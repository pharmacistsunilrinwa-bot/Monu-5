package com.monu.ai.system

import com.monu.ai.config.MonuProduction

object MonuProductionGuard {

    data class ProductionStatus(
        val productionMode: Boolean,
        val crashLogging: Boolean,
        val offlineMemory: Boolean,
        val backgroundHealth: Boolean,
        val voice: Boolean,
        val media: Boolean
    )

    fun status(): ProductionStatus {
        return ProductionStatus(
            productionMode = MonuProduction.PRODUCTION,
            crashLogging = MonuProduction.ENABLE_CRASH_LOGGING,
            offlineMemory = MonuProduction.ENABLE_OFFLINE_MEMORY,
            backgroundHealth = MonuProduction.ENABLE_BACKGROUND_HEALTH,
            voice = MonuProduction.ENABLE_VOICE,
            media = MonuProduction.ENABLE_MEDIA
        )
    }

    fun isProductionReady(): Boolean {
        val state = status()

        return state.productionMode &&
                state.crashLogging &&
                state.offlineMemory
    }
}
