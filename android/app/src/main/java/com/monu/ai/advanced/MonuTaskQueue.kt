package com.monu.ai.advanced

enum class MonuTaskStatus {
    PENDING,
    RUNNING,
    COMPLETED,
    FAILED,
    CANCELLED
}

data class MonuAiTask(
    val id: String,
    val title: String,
    val payload: String,
    var status: MonuTaskStatus =
        MonuTaskStatus.PENDING,
    val createdAt: Long =
        System.currentTimeMillis()
)

class MonuTaskQueue {

    private val tasks =
        mutableListOf<MonuAiTask>()

    fun enqueue(
        title: String,
        payload: String
    ): MonuAiTask {

        val task =
            MonuAiTask(
                id =
                    java.util.UUID
                        .randomUUID()
                        .toString(),

                title = title,
                payload = payload
            )

        tasks.add(task)

        return task
    }

    fun next():
        MonuAiTask? {

        return tasks.firstOrNull {
            it.status ==
                MonuTaskStatus.PENDING
        }
    }

    fun all():
        List<MonuAiTask> =
        tasks.toList()
}
