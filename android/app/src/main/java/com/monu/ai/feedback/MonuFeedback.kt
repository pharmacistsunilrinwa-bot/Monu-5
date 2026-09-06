package com.monu.ai.feedback

enum class MonuFeedbackType {
    LIKE,
    DISLIKE,
    INCORRECT,
    NOT_HELPFUL,
    OTHER
}

data class MonuFeedback(
    val messageId: String,
    val type: MonuFeedbackType,
    val note: String? = null,
    val createdAt: Long = System.currentTimeMillis()
)

object MonuFeedbackController {

    private val feedbackItems = mutableListOf<MonuFeedback>()

    fun submit(
        messageId: String,
        type: MonuFeedbackType,
        note: String? = null
    ): MonuFeedback {

        val feedback = MonuFeedback(
            messageId = messageId,
            type = type,
            note = note
        )

        feedbackItems.removeAll { it.messageId == messageId }
        feedbackItems.add(feedback)

        return feedback
    }

    fun get(messageId: String): MonuFeedback? {
        return feedbackItems.lastOrNull {
            it.messageId == messageId
        }
    }
}
