package com.monu.ai.security

object MonuPermissionPolicy {

    data class PermissionFeature(
        val feature: String,
        val permission: String,
        val requiresRuntimeApproval: Boolean = true
    )

    val features = listOf(

        PermissionFeature(
            feature = "Voice input",
            permission =
                "android.permission.RECORD_AUDIO"
        ),

        PermissionFeature(
            feature = "Camera",
            permission =
                "android.permission.CAMERA"
        ),

        PermissionFeature(
            feature = "Notifications",
            permission =
                "android.permission.POST_NOTIFICATIONS"
        )
    )

    fun explanationFor(
        permission: String
    ): String {

        return when (permission) {

            "android.permission.RECORD_AUDIO" ->
                "MONU needs microphone access for voice commands."

            "android.permission.CAMERA" ->
                "MONU needs camera access to capture images and media."

            "android.permission.POST_NOTIFICATIONS" ->
                "MONU uses notifications for active AI services and system status."

            else ->
                "This permission is required for the selected MONU feature."
        }
    }
}
