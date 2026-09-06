package com.monu.ai.brain

import com.monu.ai.MonuCommandType

enum class MonuExecutionTarget {
    AI_GATEWAY,
    LOCAL_MEMORY,
    MEDIA_ENGINE,
    CONNECTION_DASHBOARD,
    MODEL_SELECTOR,
    VOICE_ENGINE
}

data class MonuExecutionPlan(
    val commandType: MonuCommandType,
    val targets: List<MonuExecutionTarget>,
    val requiresNetwork: Boolean
)

class MonuExecutionPlanner {

    fun plan(
        commandType: MonuCommandType
    ): MonuExecutionPlan {

        return when (commandType) {

            MonuCommandType.CONNECTION ->
                MonuExecutionPlan(
                    commandType,
                    listOf(
                        MonuExecutionTarget
                            .CONNECTION_DASHBOARD
                    ),
                    true
                )

            MonuCommandType.MODEL ->
                MonuExecutionPlan(
                    commandType,
                    listOf(
                        MonuExecutionTarget
                            .MODEL_SELECTOR
                    ),
                    false
                )

            MonuCommandType.IMAGE,
            MonuCommandType.VIDEO,
            MonuCommandType.FILE,
            MonuCommandType.CAMERA ->
                MonuExecutionPlan(
                    commandType,
                    listOf(
                        MonuExecutionTarget
                            .MEDIA_ENGINE,
                        MonuExecutionTarget
                            .AI_GATEWAY
                    ),
                    true
                )

            MonuCommandType.VOICE ->
                MonuExecutionPlan(
                    commandType,
                    listOf(
                        MonuExecutionTarget
                            .VOICE_ENGINE,
                        MonuExecutionTarget
                            .AI_GATEWAY
                    ),
                    true
                )

            MonuCommandType.MEMORY ->
                MonuExecutionPlan(
                    commandType,
                    listOf(
                        MonuExecutionTarget
                            .LOCAL_MEMORY
                    ),
                    false
                )

            else ->
                MonuExecutionPlan(
                    commandType,
                    listOf(
                        MonuExecutionTarget
                            .LOCAL_MEMORY,
                        MonuExecutionTarget
                            .AI_GATEWAY
                    ),
                    true
                )
        }
    }
}
