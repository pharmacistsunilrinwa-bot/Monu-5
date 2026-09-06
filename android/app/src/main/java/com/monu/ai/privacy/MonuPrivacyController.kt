package com.monu.ai.privacy

enum class MonuPrivacyMode {
    STANDARD,
    PRIVATE_CHAT,
    LOCAL_ONLY
}

data class MonuPrivacyState(
    val mode: MonuPrivacyMode = MonuPrivacyMode.STANDARD,
    val saveHistory: Boolean = true,
    val allowCloudRouting: Boolean = true,
    val allowAnalytics: Boolean = false
)

class MonuPrivacyController {

    private var state = MonuPrivacyState()

    fun current(): MonuPrivacyState = state

    fun setMode(mode: MonuPrivacyMode) {
        state = when (mode) {
            MonuPrivacyMode.STANDARD ->
                MonuPrivacyState(
                    mode = mode,
                    saveHistory = true,
                    allowCloudRouting = true,
                    allowAnalytics = false
                )

            MonuPrivacyMode.PRIVATE_CHAT ->
                MonuPrivacyState(
                    mode = mode,
                    saveHistory = false,
                    allowCloudRouting = true,
                    allowAnalytics = false
                )

            MonuPrivacyMode.LOCAL_ONLY ->
                MonuPrivacyState(
                    mode = mode,
                    saveHistory = true,
                    allowCloudRouting = false,
                    allowAnalytics = false
                )
        }
    }
}
