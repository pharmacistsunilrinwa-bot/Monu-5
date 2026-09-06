package com.monu.ai.library

enum class MonuLibraryType {
    IMAGE,
    VIDEO,
    AUDIO,
    DOCUMENT,
    DOWNLOAD,
    GENERATED
}

data class MonuLibraryItem(
    val id: String,
    val name: String,
    val uri: String,
    val type: MonuLibraryType,
    val createdAt: Long =
        System.currentTimeMillis(),
    var favorite: Boolean = false
)

class MonuLibraryController {

    private val items =
        mutableListOf<MonuLibraryItem>()

    fun add(
        item: MonuLibraryItem
    ) {
        items.add(item)
    }

    fun recent(
        limit: Int = 50
    ): List<MonuLibraryItem> {

        return items
            .sortedByDescending {
                it.createdAt
            }
            .take(limit)
    }

    fun byType(
        type: MonuLibraryType
    ): List<MonuLibraryItem> {

        return items.filter {
            it.type == type
        }
    }

    fun toggleFavorite(
        id: String
    ) {
        items
            .firstOrNull {
                it.id == id
            }
            ?.let {
                it.favorite = !it.favorite
            }
    }

    fun favorites():
        List<MonuLibraryItem> {

        return items.filter {
            it.favorite
        }
    }
}
