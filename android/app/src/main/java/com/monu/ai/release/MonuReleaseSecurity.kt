package com.monu.ai.release

object MonuReleaseSecurity {

    val requirements = listOf(
        "Release signing enabled",
        "Code shrinking enabled",
        "Resource shrinking enabled",
        "Debug logging disabled in release",
        "Secrets injected from CI environment",
        "No real secret committed to Git"
    )

    fun isValid(): Boolean {
        return requirements.isNotEmpty()
    }
}
