package com.monu.ai.integration

import android.content.Context

/**
 * Runtime settings bridge.
 *
 * Connects settings screens with
 * persistent application configuration.
 */
class MonuSettingsIntegration(
    context: Context
) {

    private val preferences =
        context.getSharedPreferences(
            "monu_runtime_settings",
            Context.MODE_PRIVATE
        )

    fun setBoolean(
        key: String,
        value: Boolean
    ) {
        preferences.edit()
            .putBoolean(key, value)
            .apply()
    }

    fun getBoolean(
        key: String,
        defaultValue: Boolean = false
    ): Boolean {
        return preferences.getBoolean(
            key,
            defaultValue
        )
    }

    fun setString(
        key: String,
        value: String
    ) {
        preferences.edit()
            .putString(key, value)
            .apply()
    }

    fun getString(
        key: String,
        defaultValue: String = ""
    ): String {
        return preferences.getString(
            key,
            defaultValue
        ) ?: defaultValue
    }
}
