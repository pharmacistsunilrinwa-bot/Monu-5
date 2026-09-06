package com.monu.ai.intelligence

data class MonuCommandRecord(
    val command: String,
    val timestamp: Long = System.currentTimeMillis(),
    val category: String = "general"
)

class MonuCommandHistory(
    private val maxSize: Int = 200
) {

    private val records = mutableListOf<MonuCommandRecord>()

    fun add(
        command: String,
        category: String = "general"
    ) {
        if (command.isBlank()) return

        records.removeAll {
            it.command.equals(command, ignoreCase = true)
        }

        records.add(
            0,
            MonuCommandRecord(
                command = command,
                category = category
            )
        )

        while (records.size > maxSize) {
            records.removeLast()
        }
    }

    fun recent(limit: Int = 20): List<MonuCommandRecord> {
        return records.take(limit)
    }

    fun search(query: String): List<MonuCommandRecord> {
        if (query.isBlank()) return records.toList()

        return records.filter {
            it.command.contains(query, ignoreCase = true)
        }
    }

    fun clear() {
        records.clear()
    }
}
