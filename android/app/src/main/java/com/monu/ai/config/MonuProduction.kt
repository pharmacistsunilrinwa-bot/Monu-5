package com.monu.ai.config

object MonuProduction {

    const val APP_NAME = "MONU AI"
    const val OWNER_NAME = "Sunil Rinwa"

    const val PRODUCTION = true

    const val ENABLE_CRASH_LOGGING = true
    const val ENABLE_OFFLINE_MEMORY = true
    const val ENABLE_BACKGROUND_HEALTH = true
    const val ENABLE_VOICE = true
    const val ENABLE_MEDIA = true

    const val HEALTH_INTERVAL_MINUTES = 5L

    const val MAX_LOCAL_MESSAGE_LENGTH = 100_000
    const val MAX_ATTACHMENT_SIZE_BYTES = 500L * 1024L * 1024L
}
