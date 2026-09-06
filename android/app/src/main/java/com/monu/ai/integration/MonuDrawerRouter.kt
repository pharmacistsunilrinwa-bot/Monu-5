package com.monu.ai.integration

import com.monu.ai.navigation.MonuAppRoute
import com.monu.ai.navigation.MonuNavigationController

object MonuDrawerRouter {

    fun open(
        action: String
    ) {

        when (
            action.lowercase()
        ) {

            "chat",
            "new_chat" ->
                MonuNavigationController.navigate(
                    MonuAppRoute.Chat
                )

            "dashboard" ->
                MonuNavigationController.navigate(
                    MonuAppRoute.Dashboard
                )

            "connection" ->
                MonuNavigationController.navigate(
                    MonuAppRoute.Connection
                )

            "models" ->
                MonuNavigationController.navigate(
                    MonuAppRoute.Models
                )

            "settings" ->
                MonuNavigationController.navigate(
                    MonuAppRoute.Settings
                )

            "profile" ->
                MonuNavigationController.navigate(
                    MonuAppRoute.Profile
                )

            "notebooks" ->
                MonuNavigationController.navigate(
                    MonuAppRoute.Notebooks
                )

            "voice" ->
                MonuNavigationController.navigate(
                    MonuAppRoute.Voice
                )

            "library" ->
                MonuNavigationController.navigate(
                    MonuAppRoute.Library
                )

            "search" ->
                MonuNavigationController.navigate(
                    MonuAppRoute.Search
                )

            "timeline" ->
                MonuNavigationController.navigate(
                    MonuAppRoute.Timeline
                )

            "backup" ->
                MonuNavigationController.navigate(
                    MonuAppRoute.Backup
                )

            "privacy" ->
                MonuNavigationController.navigate(
                    MonuAppRoute.Privacy
                )
        }
    }
}
