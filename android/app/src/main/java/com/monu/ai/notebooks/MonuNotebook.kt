package com.monu.ai.notebooks

data class MonuNotebook(
    val id: String,
    var title: String,
    var content: String = "",
    val createdAt: Long =
        System.currentTimeMillis(),
    var updatedAt: Long =
        System.currentTimeMillis()
)

class MonuNotebookController {

    private val notebooks =
        mutableListOf<MonuNotebook>()

    fun create(
        title: String
    ): MonuNotebook {

        val notebook =
            MonuNotebook(
                id =
                    java.util.UUID
                        .randomUUID()
                        .toString(),
                title = title
            )

        notebooks.add(notebook)

        return notebook
    }

    fun update(
        id: String,
        content: String
    ) {
        notebooks
            .firstOrNull {
                it.id == id
            }
            ?.apply {
                this.content = content
                updatedAt =
                    System.currentTimeMillis()
            }
    }

    fun delete(
        id: String
    ) {
        notebooks.removeAll {
            it.id == id
        }
    }

    fun all():
        List<MonuNotebook> {

        return notebooks
            .sortedByDescending {
                it.updatedAt
            }
    }

    fun search(
        query: String
    ): List<MonuNotebook> {

        val q = query.lowercase()

        return notebooks.filter {
            it.title
                .lowercase()
                .contains(q) ||

            it.content
                .lowercase()
                .contains(q)
        }
    }
}
