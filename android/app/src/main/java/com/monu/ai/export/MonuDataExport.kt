package com.monu.ai.export

enum class MonuExportFormat {
    TEXT,
    MARKDOWN,
    JSON
}

data class MonuExportRequest(
    val conversationIds: List<Long>,
    val format: MonuExportFormat,
    val includeMetadata: Boolean = true
)

data class MonuExportResult(
    val success: Boolean,
    val content: String,
    val exportedAt: Long = System.currentTimeMillis()
)

class MonuDataExporter {

    fun export(
        request: MonuExportRequest,
        conversations: Map<Long, String>
    ): MonuExportResult {

        val selected = request.conversationIds
            .associateWith { conversations[it].orEmpty() }

        val content = when (request.format) {
            MonuExportFormat.TEXT ->
                selected.entries.joinToString("\n\n") {
                    "${it.key}\n${it.value}"
                }

            MonuExportFormat.MARKDOWN ->
                selected.entries.joinToString("\n\n") {
                    "# Conversation ${it.key}\n\n${it.value}"
                }

            MonuExportFormat.JSON ->
                selected.entries.joinToString(
                    prefix = "{",
                    postfix = "}",
                    separator = ","
                ) {
                    "\"${it.key}\":\"${escape(it.value)}\""
                }
        }

        return MonuExportResult(
            success = true,
            content = content
        )
    }

    private fun escape(value: String): String {
        return value
            .replace("\\", "\\\\")
            .replace("\"", "\\\"")
            .replace("\n", "\\n")
    }
}
