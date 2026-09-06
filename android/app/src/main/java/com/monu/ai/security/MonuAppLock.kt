package com.monu.ai.security

enum class MonuLockMode {
    DISABLED,
    PIN,
    BIOMETRIC
}

data class MonuLockState(
    val enabled: Boolean = false,
    val mode: MonuLockMode = MonuLockMode.DISABLED,
    val unlockedAt: Long? = null
)

class MonuAppLock {

    private var state = MonuLockState()

    fun current(): MonuLockState = state

    fun enable(mode: MonuLockMode) {
        require(mode != MonuLockMode.DISABLED)
        state = state.copy(
            enabled = true,
            mode = mode
        )
    }

    fun disable() {
        state = MonuLockState()
    }

    fun unlock(success: Boolean): Boolean {
        if (!state.enabled) return true

        if (success) {
            state = state.copy(
                unlockedAt = System.currentTimeMillis()
            )
        }

        return success
    }

    fun isUnlocked(): Boolean {
        return !state.enabled || state.unlockedAt != null
    }

    fun lock() {
        if (state.enabled) {
            state = state.copy(unlockedAt = null)
        }
    }
}
