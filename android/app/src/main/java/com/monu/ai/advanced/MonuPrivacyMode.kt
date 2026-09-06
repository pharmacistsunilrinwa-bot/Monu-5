package com.monu.ai.advanced

enum class MonuChatPrivacyMode {
    PERMANENT_MEMORY,
    TEMPORARY_SESSION
}

data class MonuChatSessionPolicy(
    val privacyMode:
        MonuChatPrivacyMode =
        MonuChatPrivacyMode.PERMANENT_MEMORY,

    val allowMemoryWrite:
        Boolean =
        privacyMode ==
        MonuChatPrivacyMode.PERMANENT_MEMORY,

    val allowConversationSave:
        Boolean =
        privacyMode ==
        MonuChatPrivacyMode.PERMANENT_MEMORY
)

object MonuPrivacyController {

    fun temporary():
        MonuChatSessionPolicy {

        return MonuChatSessionPolicy(
            privacyMode =
                MonuChatPrivacyMode.TEMPORARY_SESSION
        )
    }

    fun permanent():
        MonuChatSessionPolicy {

        return MonuChatSessionPolicy(
            privacyMode =
                MonuChatPrivacyMode.PERMANENT_MEMORY
        )
    }
}
