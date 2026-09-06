package com.monu.ai.release

data class MonuReleaseGateResult(
    val sourcePrepared: Boolean,
    val githubWorkflowPrepared: Boolean,
    val apiSecretConfiguredBeforeBuild: Boolean,
    val signingPrepared: Boolean,
    val realApkTarget: Boolean
)

object MonuReleaseGate {

    fun status(): MonuReleaseGateResult {
        return MonuReleaseGateResult(
            sourcePrepared = true,
            githubWorkflowPrepared = true,
            apiSecretConfiguredBeforeBuild = false,
            signingPrepared = true,
            realApkTarget = true
        )
    }
}
