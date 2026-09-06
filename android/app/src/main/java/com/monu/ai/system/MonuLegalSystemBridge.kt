package com.monu.ai.system

data class MonuSystemCapability(
    val name: String,
    val description: String,
    val requiresPermission: Boolean,
    val requiresUserConsent: Boolean
)

object MonuLegalSystemBridge {

    fun capabilities(): List<MonuSystemCapability> {

        return listOf(

            MonuSystemCapability(
                name = "Share Content",
                description =
                    "Uses Android system share sheet",
                requiresPermission = false,
                requiresUserConsent = true
            ),

            MonuSystemCapability(
                name = "Open Settings",
                description =
                    "Opens official Android settings screens",
                requiresPermission = false,
                requiresUserConsent = true
            ),

            MonuSystemCapability(
                name = "Clipboard",
                description =
                    "Copies or reads clipboard through Android APIs",
                requiresPermission = false,
                requiresUserConsent = false
            ),

            MonuSystemCapability(
                name = "Notifications",
                description =
                    "Displays MONU notifications",
                requiresPermission = true,
                requiresUserConsent = true
            ),

            MonuSystemCapability(
                name = "Camera",
                description =
                    "Uses camera after runtime permission",
                requiresPermission = true,
                requiresUserConsent = true
            ),

            MonuSystemCapability(
                name = "Microphone",
                description =
                    "Uses microphone after runtime permission",
                requiresPermission = true,
                requiresUserConsent = true
            )
        )
    }
}
