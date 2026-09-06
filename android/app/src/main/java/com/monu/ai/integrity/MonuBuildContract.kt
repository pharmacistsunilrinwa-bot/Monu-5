package com.monu.ai.integrity

object MonuBuildContract {

    const val APP_NAME = "MONU AI"
    const val OWNER = "Sunil Rinwa"

    val requiredCoreModules = listOf(
        "MonuApplication",
        "MainActivity",
        "MonuBrain",
        "MonuAiOrchestrator",
        "MonuRuntime",
        "MonuNavigator",
        "MonuPersistenceManager",
        "MonuSyncEngine"
    )

    fun validateProjectModules(
        availableModules: Collection<String>
    ): IntegrityResult {
        val missing = requiredCoreModules.filterNot {
            module ->
            availableModules.any { it.contains(module) }
        }

        return IntegrityResult(
            valid = missing.isEmpty(),
            missingModules = missing
        )
    }
}

data class IntegrityResult(
    val valid: Boolean,
    val missingModules: List<String>
)
