package com.monu.ai.integration

import com.monu.ai.navigation.MonuAppRoute
import com.monu.ai.navigation.MonuNavigationController

object MonuAppIntegration {

    fun openFeature(
        featureId: String
    ): Boolean {

        val feature =
            MonuFeatureRegistry.find(
                featureId
            ) ?: return false

        val route = when (
            feature.route
        ) {

            "chat" ->
                MonuAppRoute.Chat

            "dashboard" ->
                MonuAppRoute.Dashboard

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

            "search" ->
                MonuAppRoute.Search

            "timeline" ->
                MonuAppRoute.Archive

            "backup" ->
                MonuAppRoute.Archive

            "privacy" ->
                MonuAppRoute.Privacy

            else ->
                return false
        }

        MonuNavigationController.navigate(
            route
        )

        return true
    }
}
