package com.monu.ai.security

data class MonuScreenPrivacyState(
    val blockScreenshots: Boolean = false,
    val hideInRecents: Boolean = false,
    val secureScreenEnabled: Boolean = false
)

class MonuScreenPrivacyController {

    private var state = MonuScreenPrivacyState()

    fun current(): MonuScreenPrivacyState = state

    fun enableSecureMode(
        blockScreenshots: Boolean = true,
        hideInRecents: Boolean = true
    ) {
        state = MonuScreenPrivacyState(
            blockScreenshots = blockScreenshots,
            hideInRecents = hideInRecents,
            secureScreenEnabled = true
        )
    }

    fun disableSecureMode() {
        state = MonuScreenPrivacyState()
    }
}
