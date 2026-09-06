package com.monu.ai.navigation

sealed class MonuAppRoute(val route: String) {

    data object Chat : MonuAppRoute("chat")

    data object Connection :
        MonuAppRoute("connection")

    data object Models :
        MonuAppRoute("models")

    data object Settings :
        MonuAppRoute("settings")

    data object Profile :
        MonuAppRoute("profile")

    data object Notebooks :
        MonuAppRoute("notebooks")

    data object Voice :
        MonuAppRoute("voice")

    data object Library :
        MonuAppRoute("library")

    data object Search :
        MonuAppRoute("search")

    data object Timeline :
        MonuAppRoute("timeline")

    data object Backup :
        MonuAppRoute("backup")

    data object Privacy :
        MonuAppRoute("privacy")

    data object Dashboard :
        MonuAppRoute("dashboard")
}
