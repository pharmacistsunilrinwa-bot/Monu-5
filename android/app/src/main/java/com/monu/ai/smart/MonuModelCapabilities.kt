package com.monu.ai.smart

data class MonuModelCapability(
    val modelId: String,
    val supportsText: Boolean = true,
    val supportsImages: Boolean = false,
    val supportsFiles: Boolean = false,
    val supportsStreaming: Boolean = true,
    val supportsThinking: Boolean = false
)

object MonuModelCapabilities {

    private val capabilities = mutableMapOf<String, MonuModelCapability>()

    fun register(capability: MonuModelCapability) {
        capabilities[capability.modelId] = capability
    }

    fun get(modelId: String): MonuModelCapability {
        return capabilities[modelId]
            ?: MonuModelCapability(modelId = modelId)
    }

    fun supports(modelId: String, feature: String): Boolean {
        val capability = get(modelId)

        return when (feature.lowercase()) {
            "text" -> capability.supportsText
            "image", "images" -> capability.supportsImages
            "file", "files" -> capability.supportsFiles
            "stream", "streaming" -> capability.supportsStreaming
            "thinking", "reasoning" -> capability.supportsThinking
            else -> false
        }
    }
}
