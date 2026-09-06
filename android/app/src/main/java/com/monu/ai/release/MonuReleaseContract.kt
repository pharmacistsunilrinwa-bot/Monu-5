package com.monu.ai.release

data class MonuReleaseContract(
    val minSdkConfigured: Boolean,
    val targetSdkConfigured: Boolean,
    val releaseBuildConfigured: Boolean,
    val signingConfigured: Boolean,
    val shrinkingConfigured: Boolean,
    val secretInjectionConfigured: Boolean
) {
    val readyForRelease: Boolean
        get() =
            minSdkConfigured &&
            targetSdkConfigured &&
            releaseBuildConfigured &&
            signingConfigured &&
            shrinkingConfigured &&
            secretInjectionConfigured
}
