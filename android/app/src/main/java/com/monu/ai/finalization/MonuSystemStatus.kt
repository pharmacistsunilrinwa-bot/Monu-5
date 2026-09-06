package com.monu.ai.finalization

data class MonuSystemStatus(
    val brain: Boolean,
    val runtime: Boolean,
    val chat: Boolean,
    val memory: Boolean,
    val network: Boolean,
    val navigation: Boolean,
    val persistence: Boolean,
    val sync: Boolean
) {
    val architectureReady: Boolean
        get() = brain &&
                runtime &&
                chat &&
                memory &&
                network &&
                navigation &&
                persistence &&
                sync
}

object MonuSystemStatusFactory {

    fun production(): MonuSystemStatus {
        return MonuSystemStatus(
            brain = true,
            runtime = true,
            chat = true,
            memory = true,
            network = true,
            navigation = true,
            persistence = true,
            sync = true
        )
    }
}
