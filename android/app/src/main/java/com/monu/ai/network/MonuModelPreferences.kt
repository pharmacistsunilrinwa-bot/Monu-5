package com.monu.ai.network

import android.content.Context

class MonuModelPreferences(
    context: Context
) {

    private val preferences =
        context.getSharedPreferences(
            "monu_model_preferences",
            Context.MODE_PRIVATE
        )

    fun selectedModel(): String {

        return preferences.getString(
            "selected_model",
            MonuModelRegistry
                .defaultModel()
                .id
        )
            ?: MonuModelRegistry
                .defaultModel()
                .id
    }

    fun selectModel(
        modelId: String
    ) {

        preferences.edit()
            .putString(
                "selected_model",
                modelId
            )
            .apply()
    }
}
