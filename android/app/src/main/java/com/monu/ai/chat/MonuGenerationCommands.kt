package com.monu.ai.chat

sealed class MonuGenerationCommand {

    data class Start(
        val conversationId: Long?,
        val prompt: String
    ) : MonuGenerationCommand()

    object Stop : MonuGenerationCommand()

    data class Regenerate(
        val messageId: Long
    ) : MonuGenerationCommand()

    data class Retry(
        val messageId: Long
    ) : MonuGenerationCommand()

    data class EditAndResend(
        val messageId: Long,
        val updatedPrompt: String
    ) : MonuGenerationCommand()
}

class MonuGenerationCommandRouter {

    fun route(command: MonuGenerationCommand): String {
        return when (command) {
            is MonuGenerationCommand.Start ->
                "START_GENERATION"

            is MonuGenerationCommand.Stop ->
                "STOP_GENERATION"

            is MonuGenerationCommand.Regenerate ->
                "REGENERATE_RESPONSE"

            is MonuGenerationCommand.Retry ->
                "RETRY_REQUEST"

            is MonuGenerationCommand.EditAndResend ->
                "EDIT_AND_RESEND"
        }
    }
}
