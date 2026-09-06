package com.monu.ai.appfeatures

data class MonuBackupInfo(
    val id: String,
    val createdAt: Long =
        System.currentTimeMillis(),
    val version: Int = 1,
    val encrypted: Boolean = true
)

enum class MonuBackupAction {
    EXPORT_CHAT,
    EXPORT_ALL_CHATS,
    BACKUP_APP_DATA,
    RESTORE_APP_DATA
}

class MonuBackupController {

    fun createBackupInfo():
        MonuBackupInfo {

        return MonuBackupInfo(
            id = java.util.UUID
                .randomUUID()
                .toString()
        )
    }

    fun supportedActions():
        List<MonuBackupAction> {

        return MonuBackupAction.entries
    }
}
