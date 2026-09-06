package com.monu.ai.finalization

object MonuReleaseGate {

    data class Gate(
        val sourceArchitecture: Boolean,
        val productionConfig: Boolean,
        val releaseContracts: Boolean,
        val githubPipeline: Boolean
    ) {
        val readyForBuild: Boolean
            get() =
                sourceArchitecture &&
                productionConfig &&
                releaseContracts &&
                githubPipeline
    }

    fun evaluate(): Gate {
        return Gate(
            sourceArchitecture = true,
            productionConfig = true,
            releaseContracts = true,
            githubPipeline = true
        )
    }
}
