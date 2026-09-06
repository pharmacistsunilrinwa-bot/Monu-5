package com.monu.ai.release

object MonuBuildEnvironment {

    data class BuildEnvironment(
        val githubActions: Boolean,
        val secretInjection: Boolean,
        val releaseArtifact: Boolean
    )

    fun expected(): BuildEnvironment {
        return BuildEnvironment(
            githubActions = true,
            secretInjection = true,
            releaseArtifact = true
        )
    }
}
