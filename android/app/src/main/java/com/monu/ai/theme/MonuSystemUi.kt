package com.monu.ai.theme

data class MonuSystemUiState(
    val edgeToEdge: Boolean = true,
    val statusBarVisible: Boolean = true,
    val navigationBarVisible: Boolean = true,
    val immersiveMode: Boolean = false
)

class MonuSystemUiController {

    private var state = MonuSystemUiState()

    fun current(): MonuSystemUiState = state

    fun setImmersive(enabled: Boolean) {
        state = state.copy(
            immersiveMode = enabled,
            statusBarVisible = !enabled,
            navigationBarVisible = !enabled
        )
    }

    fun setEdgeToEdge(enabled: Boolean) {
        state = state.copy(edgeToEdge = enabled)
    }
}
