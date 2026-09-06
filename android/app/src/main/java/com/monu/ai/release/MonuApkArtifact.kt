package com.monu.ai.release

data class MonuApkArtifact(
    val type: String,
    val signed: Boolean,
    val installable: Boolean,
    val distribution: String
)

object MonuApkArtifactContract {

    fun production(): MonuApkArtifact {
        return MonuApkArtifact(
            type = "APK",
            signed = true,
            installable = true,
            distribution = "Private Personal Use"
        )
    }
}
