package com.monu.ai.advanced

data class MonuConversationBranch(
    val branchId: String,
    val parentConversationId: Long,
    val sourceMessageId: Long?,
    val title: String,
    val createdAt: Long =
        System.currentTimeMillis()
)

class MonuBranchController {

    private val branches =
        mutableListOf<MonuConversationBranch>()

    fun createBranch(
        parentConversationId: Long,
        sourceMessageId: Long?,
        title: String
    ): MonuConversationBranch {

        val branch =
            MonuConversationBranch(
                branchId =
                    java.util.UUID
                        .randomUUID()
                        .toString(),

                parentConversationId =
                    parentConversationId,

                sourceMessageId =
                    sourceMessageId,

                title = title
            )

        branches.add(branch)

        return branch
    }

    fun getBranches(
        conversationId: Long
    ): List<MonuConversationBranch> {

        return branches.filter {
            it.parentConversationId ==
                conversationId
        }
    }
}
