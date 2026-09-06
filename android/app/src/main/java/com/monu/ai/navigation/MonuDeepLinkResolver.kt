package com.monu.ai.navigation

object MonuDeepLinkResolver {

    fun resolve(
        value: String
    ): MonuAppRoute {

        return when {

            value.contains("settings", true) ->
                MonuAppRoute.Settings

            value.contains("profile", true) ->
                MonuAppRoute.Profile

            value.contains("notebook", true) ->
                MonuAppRoute.Notebooks

            value.contains("voice", true) ->
                MonuAppRoute.Voice

            value.contains("connection", true) ->
                MonuAppRoute.Connection

            value.contains("model", true) ->
                MonuAppRoute.ModelSelector

            value.contains("dashboard", true) ->
                MonuAppRoute.Dashboard

            else ->
                MonuAppRoute.Chat
        }
    }
}
