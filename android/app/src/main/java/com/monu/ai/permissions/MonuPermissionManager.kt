package com.monu.ai.permissions

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

enum class MonuPermission(
    val permission: String
) {
    MICROPHONE(
        Manifest.permission.RECORD_AUDIO
    ),

    CAMERA(
        Manifest.permission.CAMERA
    ),

    NOTIFICATIONS(
        if (Build.VERSION.SDK_INT >= 33)
            Manifest.permission.POST_NOTIFICATIONS
        else
            ""
    )
}

class MonuPermissionManager(
    private val activity: Activity
) {

    fun has(
        permission: MonuPermission
    ): Boolean {

        if (permission.permission.isBlank()) {
            return true
        }

        return ContextCompat.checkSelfPermission(
            activity,
            permission.permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    fun request(
        permission: MonuPermission,
        requestCode: Int
    ) {

        if (permission.permission.isBlank()) {
            return
        }

        ActivityCompat.requestPermissions(
            activity,
            arrayOf(permission.permission),
            requestCode
        )
    }

    fun requestAllCore(
        requestCode: Int
    ) {

        val permissions =
            MonuPermission.entries
                .map { it.permission }
                .filter { it.isNotBlank() }
                .filter {
                    ContextCompat.checkSelfPermission(
                        activity,
                        it
                    ) != PackageManager.PERMISSION_GRANTED
                }

        if (permissions.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                activity,
                permissions.toTypedArray(),
                requestCode
            )
        }
    }
}
