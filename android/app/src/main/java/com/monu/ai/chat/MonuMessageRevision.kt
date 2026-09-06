package com.monu.ai.chat

data class MonuMessageRevision(
    val messageId: Long,
    val originalText: String,
    val revisedText: String,
    val createdAt: Long = System.currentTimeMillis()
)

data class MonuResponseVariant(
    val messageId: Long,
    val variantId: String,
    val content: String,
    val createdAt: Long = System.currentTimeMillis(),
    val selected: Boolean = false
)

class MonuMessageRevisionController {

    private val revisions = mutableListOf<MonuMessageRevision>()
    private val variants = mutableMapOf<Long, MutableList<MonuResponseVariant>>()

    fun edit(
        messageId: Long,
        original: String,
        revised: String
    ): MonuMessageRevision {
        return MonuMessageRevision(
            messageId = messageId,
            originalText = original,
            revisedText = revised
        ).also { revisions.add(it) }
    }

    fun addVariant(
        messageId: Long,
        content: String
    ): MonuResponseVariant {
        val list = variants.getOrPut(messageId) { mutableListOf() }

        val variant = MonuResponseVariant(
            messageId = messageId,
            variantId = "variant_${messageId}_${list.size + 1}",
            content = content
        )

        list.add(variant)
        return variant
    }

    fun variants(messageId: Long): List<MonuResponseVariant> {
        return variants[messageId]?.toList().orEmpty()
    }
}
