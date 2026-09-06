package com.monu.ai.recovery

data class MonuUndoAction(
    val label: String,
    val execute: () -> Unit
)

sealed class MonuRecoveryState {
    data object Idle : MonuRecoveryState()

    data class Error(
        val message: String,
        val retryable: Boolean = true
    ) : MonuRecoveryState()

    data object Retrying : MonuRecoveryState()

    data object Recovered : MonuRecoveryState()
}

object MonuRecoveryController {

    private var lastUndoAction: MonuUndoAction? = null

    fun registerUndo(
        label: String,
        action: () -> Unit
    ) {
        lastUndoAction = MonuUndoAction(label, action)
    }

    fun undo(): Boolean {
        val action = lastUndoAction ?: return false

        return try {
            action.execute()
            lastUndoAction = null
            true
        } catch (_: Exception) {
            false
        }
    }

    fun clearUndo() {
        lastUndoAction = null
    }

    fun error(
        message: String,
        retryable: Boolean = true
    ): MonuRecoveryState {
        return MonuRecoveryState.Error(
            message = message,
            retryable = retryable
        )
    }
}
