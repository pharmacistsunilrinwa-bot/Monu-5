package com.monu.ai.appfeatures

enum class MonuSearchType {
    CHAT,
    MESSAGE,
    FILE,
    IMAGE,
    VIDEO,
    NOTEBOOK,
    MEMORY
}

data class MonuSearchResult(
    val id: String,
    val title: String,
    val preview: String,
    val type: MonuSearchType
)

class MonuUniversalSearch {

    private val results =
        mutableListOf<MonuSearchResult>()

    fun index(
        result: MonuSearchResult
    ) {
        results.removeAll {
            it.id == result.id
        }

        results.add(result)
    }

    fun search(
        query: String
    ): List<MonuSearchResult> {

        if (query.isBlank()) {
            return emptyList()
        }

        val normalized =
            query.lowercase()

        return results.filter {

            it.title
                .lowercase()
                .contains(normalized) ||

            it.preview
                .lowercase()
                .contains(normalized)
        }
    }
}
