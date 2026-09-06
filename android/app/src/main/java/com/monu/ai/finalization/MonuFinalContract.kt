package com.monu.ai.finalization

object MonuFinalContract {

    const val APP_NAME = "MONU AI"
    const val OWNER = "Sunil Rinwa"

    val productionModules = listOf(
        "Central AI Brain",
        "AI Runtime",
        "Chat Engine",
        "Permanent Memory",
        "Voice System",
        "Media Pipeline",
        "Gemini Provider",
        "Quad Router",
        "Connection Controller",
        "Health Monitor",
        "Navigation",
        "Offline Sync",
        "Security",
        "Permissions",
        "System Integration"
    )

    fun moduleCount(): Int = productionModules.size
}
