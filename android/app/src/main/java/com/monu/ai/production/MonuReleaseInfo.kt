package com.monu.ai.production

object MonuReleaseInfo {

    const val APP_NAME = "MONU"
    const val EDITION = "Personal AI"
    const val RELEASE_TYPE = "Production APK"

    const val SERVER_REQUIRED = false
    const val DIRECT_GEMINI_AVAILABLE = true
    const val OFFLINE_ARCHITECTURE_AVAILABLE = true

    const val DISTRIBUTION = "Private Personal Use"

    fun summary(): String {
        return "$APP_NAME $EDITION | $RELEASE_TYPE"
    }
}
