package com.monu.ai.intelligence

enum class MonuActivityType {
    CHAT,
    VOICE,
    IMAGE,
    VIDEO,
    FILE,
    NOTEBOOK,
    SEARCH,
    CONNECTION,
    SYSTEM
}

data class MonuActivityEvent(
    val id: String,
    val type: MonuActivityType,
    val title: String,
    val detail: String = "",
    val timestamp: Long = System.currentTimeMillis()
)

class MonuActivityTimeline {

    private val events = mutableListOf<MonuActivityEvent>()

    fun add(event: MonuActivityEvent) {
        events.add(0, event)

        if (events.size > 500) {
            events.removeLast()
        }
    }

    fun recent(limit: Int = 50): List<MonuActivityEvent> {
        return events.take(limit)
    }

    fun byType(type: MonuActivityType): List<MonuActivityEvent> {
        return events.filter { it.type == type }
    }

    fun clear() {
        events.clear()
    }
}
