package com.monu.ai.release

object MonuBuildInputs {

    const val GEMINI_API_KEY_SECRET = "MONU_GEMINI_API_KEY"
    const val SERVER_URL_SECRET = "MONU_SERVER_URL"

    val requiredSecrets = listOf(
        GEMINI_API_KEY_SECRET
    )

    val optionalSecrets = listOf(
        SERVER_URL_SECRET
    )
}
