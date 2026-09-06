package com.monu.ai.persistence

data class MonuPersistentSession(
    val conversationId: Long?,
    val model: String?,
    val route: String?,
    val updatedAt: Long =
        System.currentTimeMillis()
)

class MonuPersistentSessionController(
    private val persistence:
        MonuPersistenceManager
) {

    fun save(
        session: MonuPersistentSession
    ) {

        session.conversationId?.let {
            persistence.saveDraft(
                it,
                ""
            )
        }

        session.model?.let {
            persistence.saveSelectedModel(
                it
            )
        }

        session.route?.let {
            persistence.saveLastRoute(
                it
            )
        }
    }

    fun restore(): MonuPersistentSession {

        val conversationId =
            persistence.draftConversationId()
                .takeIf { it >= 0L }

        val model =
            persistence.selectedModel()
                .takeIf {
                    it.isNotBlank()
                }

        val route =
            persistence.lastRoute()
                .takeIf {
                    it.isNotBlank()
                }

        return MonuPersistentSession(
            conversationId =
                conversationId,
            model = model,
            route = route
        )
    }
}
