package com.monu.ai.persistence

import android.content.Context

class MonuPersistenceManager(
    context: Context
) {

    private val storage =
        MonuPreferences(context)

    fun saveSelectedModel(model: String) {
        storage.putString(
            MonuStorageKeys.SELECTED_MODEL,
            model
        )
    }

    fun selectedModel(): String {
        return storage.getString(
            MonuStorageKeys.SELECTED_MODEL
        )
    }

    fun saveServerUrl(url: String) {
        storage.putString(
            MonuStorageKeys.SERVER_URL,
            url
        )
    }

    fun serverUrl(): String {
        return storage.getString(
            MonuStorageKeys.SERVER_URL
        )
    }

    fun saveDraft(
        conversationId: Long,
        draft: String
    ) {
        storage.putLong(
            MonuStorageKeys.DRAFT_CONVERSATION,
            conversationId
        )

        storage.putString(
            MonuStorageKeys.DRAFT,
            draft
        )
    }

    fun draft(): String {
        return storage.getString(
            MonuStorageKeys.DRAFT
        )
    }

    fun draftConversationId(): Long {
        return storage.getLong(
            MonuStorageKeys.DRAFT_CONVERSATION,
            -1L
        )
    }

    fun clearDraft() {
        storage.remove(
            MonuStorageKeys.DRAFT
        )

        storage.remove(
            MonuStorageKeys.DRAFT_CONVERSATION
        )
    }

    fun saveLastRoute(route: String) {
        storage.putString(
            MonuStorageKeys.LAST_ROUTE,
            route
        )
    }

    fun lastRoute(): String {
        return storage.getString(
            MonuStorageKeys.LAST_ROUTE
        )
    }

    fun setOnboardingComplete(
        completed: Boolean
    ) {
        storage.putBoolean(
            MonuStorageKeys.ONBOARDING_COMPLETE,
            completed
        )
    }

    fun isOnboardingComplete(): Boolean {
        return storage.getBoolean(
            MonuStorageKeys.ONBOARDING_COMPLETE,
            false
        )
    }
}
