package com.monu.ai.security

enum class MonuDataSensitivity {
    PUBLIC,
    PRIVATE,
    SENSITIVE,
    SECRET
}

data class MonuProtectedData(
    val id: String,
    val type: String,
    val sensitivity: MonuDataSensitivity,
    val createdAt: Long = System.currentTimeMillis()
)

object MonuDataSecurity {

    fun requiresProtection(
        sensitivity: MonuDataSensitivity
    ): Boolean {
        return sensitivity == MonuDataSensitivity.SENSITIVE ||
               sensitivity == MonuDataSensitivity.SECRET
    }

    fun canExport(
        sensitivity: MonuDataSensitivity,
        authenticated: Boolean
    ): Boolean {
        return when (sensitivity) {
            MonuDataSensitivity.PUBLIC -> true
            MonuDataSensitivity.PRIVATE -> authenticated
            MonuDataSensitivity.SENSITIVE -> authenticated
            MonuDataSensitivity.SECRET -> false
        }
    }
}
