package com.monu.ai.integration

import android.net.Uri
import com.monu.ai.navigation.MonuAppRoute

object MonuDeepLinkResolver {

    fun resolve(
        uri: Uri?
    ): MonuAppRoute? {

        val value =
            uri?.lastPathSegment
                ?.lowercase()
                ?: return null

        return when (value) {

            "chat" ->
                MonuAppRoute.Chat

            "connection" ->
                MonuAppRoute.Connection

            "models" ->
                MonuAppRoute.ModelSelector

            "settings" ->
                MonuAppRoute.Settings

            "profile" ->
                MonuAppRoute.Profile

            "notebooks" ->
                MonuAppRoute.Notebooks

            "voice" ->
                MonuAppRoute.Voice

            "library" ->
                MonuAppRoute.Library

            "dashboard" ->
                MonuAppRoute.Dashboard

            else -> null
        }
    }
}
