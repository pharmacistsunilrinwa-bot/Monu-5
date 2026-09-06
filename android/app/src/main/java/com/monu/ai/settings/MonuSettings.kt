package com.monu.ai.settings

enum class MonuThemeMode {
    SYSTEM,
    LIGHT,
    DARK
}

enum class MonuFontSize {
    SMALL,
    NORMAL,
    LARGE,
    EXTRA_LARGE
}

data class MonuAppSettings(
    val themeMode: MonuThemeMode = MonuThemeMode.SYSTEM,
    val fontSize: MonuFontSize = MonuFontSize.NORMAL,
    val dynamicTheme: Boolean = true,
    val voiceEnabled: Boolean = true,
    val notificationsEnabled: Boolean = true,
    val autoDownloadEnabled: Boolean = true,
    val offlineMode: Boolean = false
)

class MonuSettingsController {

    private var settings = MonuAppSettings()

    fun current(): MonuAppSettings = settings

    fun update(
        newSettings: MonuAppSettings
    ) {
        settings = newSettings
    }

    fun reset() {
        settings = MonuAppSettings()
    }
}
