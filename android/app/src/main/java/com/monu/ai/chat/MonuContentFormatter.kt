package com.monu.ai.chat

sealed class MonuContentBlock {
    data class Text(val value: String) : MonuContentBlock()
    data class Code(
        val language: String?,
        val value: String
    ) : MonuContentBlock()
}

object MonuContentFormatter {

    fun parse(content: String): List<MonuContentBlock> {
        val blocks = mutableListOf<MonuContentBlock>()

        val regex = Regex(
            pattern = "```([a-zA-Z0-9_+-]*)\\n([\\s\\S]*?)```"
        )

        var lastIndex = 0

        regex.findAll(content).forEach { match ->
            if (match.range.first > lastIndex) {
                blocks.add(
                    MonuContentBlock.Text(
                        content.substring(
                            lastIndex,
                            match.range.first
                        )
                    )
                )
            }

            val language = match.groupValues[1]
                .takeIf { it.isNotBlank() }

            blocks.add(
                MonuContentBlock.Code(
                    language = language,
                    value = match.groupValues[2]
                )
            )

            lastIndex = match.range.last + 1
        }

        if (lastIndex < content.length) {
            blocks.add(
                MonuContentBlock.Text(
                    content.substring(lastIndex)
                )
            )
        }

        if (blocks.isEmpty()) {
            blocks.add(MonuContentBlock.Text(content))
        }

        return blocks
    }

    fun extractPlainText(content: String): String {
        return content
            .replace(Regex("```[\\s\\S]*?```"), "[CODE BLOCK]")
            .replace(Regex("\\*\\*(.*?)\\*\\*"), "$1")
            .replace(Regex("`(.*?)`"), "$1")
    }
}
