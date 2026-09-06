package com.monu.ai.advanced

data class MonuCapability(
    val id: String,
    val name: String,
    val enabled: Boolean = true
)

object MonuCapabilities {

    val all = listOf(

        MonuCapability(
            "chat",
            "AI Chat"
        ),

        MonuCapability(
            "voice",
            "Voice Interaction"
        ),

        MonuCapability(
            "memory",
            "Permanent Memory"
        ),

        MonuCapability(
            "temporary_chat",
            "Temporary Chat"
        ),

        MonuCapability(
            "media",
            "Media Processing"
        ),

        MonuCapability(
            "files",
            "File Analysis"
        ),

        MonuCapability(
            "web_knowledge",
            "Web Knowledge Routing"
        ),

        MonuCapability(
            "server",
            "MONU Server Routing"
        ),

        MonuCapability(
            "quad_router",
            "Quad Routing"
        ),

        MonuCapability(
            "generation_control",
            "Stop And Regenerate"
        ),

        MonuCapability(
            "conversation_branch",
            "Conversation Branching"
        ),

        MonuCapability(
            "task_queue",
            "AI Task Queue"
        )
    )

    fun enabled():
        List<MonuCapability> {

        return all.filter {
            it.enabled
        }
    }
}
