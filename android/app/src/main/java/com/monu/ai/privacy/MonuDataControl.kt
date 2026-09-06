package com.monu.ai.privacy

enum class MonuDeletionScope {
    CONVERSATION,
    MEMORY,
    MEDIA,
    CACHE,
    ALL_LOCAL_DATA
}

data class MonuDeletionRequest(
    val scope: MonuDeletionScope,
    val confirmationRequired: Boolean = true
)

class MonuDataControl {

    fun requiresConfirmation(
        scope: MonuDeletionScope
    ): Boolean {
        return when (scope) {
            MonuDeletionScope.CACHE -> false
            else -> true
        }
    }

    fun deletionAction(
        scope: MonuDeletionScope
    ): String {
        return when (scope) {
            MonuDeletionScope.CONVERSATION ->
                "DELETE_CONVERSATION"

            MonuDeletionScope.MEMORY ->
                "DELETE_MEMORY"

            MonuDeletionScope.MEDIA ->
                "DELETE_MEDIA"

            MonuDeletionScope.CACHE ->
                "CLEAR_CACHE"

            MonuDeletionScope.ALL_LOCAL_DATA ->
                "FACTORY_RESET_LOCAL_DATA"
        }
    }
}
