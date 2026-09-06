package com.monu.ai.theme

enum class MonuThemeMode {
    SYSTEM,
    LIGHT,
    DARK,
    DYNAMIC
}

enum class MonuMoodTheme {
    CALM,
    FOCUS,
    CREATIVE,
    ENERGY,
    NIGHT
}

data class MonuThemeState(
    val mode: MonuThemeMode = MonuThemeMode.SYSTEM,
    val mood: MonuMoodTheme = MonuMoodTheme.CALM,
    val useDynamicColors: Boolean = true
)

class MonuThemeEngine {

    private var state = MonuThemeState()

    fun current(): MonuThemeState = state

    fun setMode(mode: MonuThemeMode) {
        state = state.copy(mode = mode)
    }

    fun setMood(mood: MonuMoodTheme) {
        state = state.copy(mood = mood)
    }

    fun enableDynamicColors(enabled: Boolean) {
        state = state.copy(useDynamicColors = enabled)
    }
}
