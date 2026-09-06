package com.monu.ai.release

data class MonuReleaseConfig(
    val appName: String = "MONU",
    val channel: MonuReleaseChannel = MonuReleaseChannel.PRODUCTION,
    val realApk: Boolean = true,
    val directGeminiEnabled: Boolean = true,
    val serverRequired: Boolean = false,
    val githubBuildEnabled: Boolean = true,
    val termuxBuildRequired: Boolean = false
)
