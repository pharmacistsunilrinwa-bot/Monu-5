package com.monu.ai.workspace

import android.net.Uri
import java.util.UUID

enum class MonuAttachmentType {
    IMAGE,
    VIDEO,
    AUDIO,
    PDF,
    DOCUMENT,
    UNKNOWN
}

data class MonuAttachment(
    val id: String = UUID.randomUUID().toString(),
    val uri: Uri,
    val name: String = "",
    val mimeType: String = "",
    val type: MonuAttachmentType = MonuAttachmentType.UNKNOWN,
    val sizeBytes: Long? = null
)

class MonuAttachmentWorkspace {

    private val attachments = mutableListOf<MonuAttachment>()

    fun add(item: MonuAttachment) {
        if (attachments.none { it.uri == item.uri }) {
            attachments += item
        }
    }

    fun remove(id: String) {
        attachments.removeAll { it.id == id }
    }

    fun clear() {
        attachments.clear()
    }

    fun all(): List<MonuAttachment> {
        return attachments.toList()
    }

    fun count(): Int = attachments.size

    fun hasAttachments(): Boolean = attachments.isNotEmpty()
}
