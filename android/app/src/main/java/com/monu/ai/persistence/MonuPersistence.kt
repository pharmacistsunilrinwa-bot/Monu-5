package com.monu.ai.persistence

interface MonuPersistence {
    fun putString(key: String, value: String)
    fun getString(key: String, defaultValue: String = ""): String

    fun putLong(key: String, value: Long)
    fun getLong(key: String, defaultValue: Long = 0L): Long

    fun putBoolean(key: String, value: Boolean)
    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean

    fun remove(key: String)
    fun clear()
}
