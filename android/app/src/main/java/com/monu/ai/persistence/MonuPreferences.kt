package com.monu.ai.persistence

import android.content.Context
import android.content.SharedPreferences

class MonuPreferences(
    context: Context
) : MonuPersistence {

    private val preferences: SharedPreferences =
        context.getSharedPreferences(
            "monu_persistent_state",
            Context.MODE_PRIVATE
        )

    override fun putString(
        key: String,
        value: String
    ) {
        preferences.edit()
            .putString(key, value)
            .apply()
    }

    override fun getString(
        key: String,
        defaultValue: String
    ): String {
        return preferences.getString(
            key,
            defaultValue
        ) ?: defaultValue
    }

    override fun putLong(
        key: String,
        value: Long
    ) {
        preferences.edit()
            .putLong(key, value)
            .apply()
    }

    override fun getLong(
        key: String,
        defaultValue: Long
    ): Long {
        return preferences.getLong(
            key,
            defaultValue
        )
    }

    override fun putBoolean(
        key: String,
        value: Boolean
    ) {
        preferences.edit()
            .putBoolean(key, value)
            .apply()
    }

    override fun getBoolean(
        key: String,
        defaultValue: Boolean
    ): Boolean {
        return preferences.getBoolean(
            key,
            defaultValue
        )
    }

    override fun remove(key: String) {
        preferences.edit()
            .remove(key)
            .apply()
    }

    override fun clear() {
        preferences.edit()
            .clear()
            .apply()
    }
}
