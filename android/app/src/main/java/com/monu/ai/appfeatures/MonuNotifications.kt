package com.monu.ai.appfeatures

enum class MonuNotificationType {
    AI_RESPONSE,
    DOWNLOAD,
    TASK,
    SYSTEM,
    HEALTH
}

data class MonuNotification(
    val id: String,
    val title: String,
    val message: String,
    val type: MonuNotificationType,
    val timestamp: Long =
        System.currentTimeMillis(),
    var read: Boolean = false
)

class MonuNotificationCenter {

    private val notifications =
        mutableListOf<MonuNotification>()

    fun add(
        title: String,
        message: String,
        type: MonuNotificationType
    ) {

        notifications.add(
            MonuNotification(
                id =
                    java.util.UUID
                        .randomUUID()
                        .toString(),

                title = title,
                message = message,
                type = type
            )
        )
    }

    fun all():
        List<MonuNotification> {

        return notifications
            .sortedByDescending {
                it.timestamp
            }
    }

    fun unreadCount():
        Int {

        return notifications.count {
            !it.read
        }
    }

    fun markAllRead() {

        notifications.forEach {
            it.read = true
        }
    }
}
