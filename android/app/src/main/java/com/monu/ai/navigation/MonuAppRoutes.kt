package com.monu.ai.navigation

sealed class MonuAppRoute(val route: String) {

    data object Onboarding : MonuAppRoute("onboarding")
    data object Chat : MonuAppRoute("chat")
    data object Dashboard : MonuAppRoute("dashboard")
    data object Connection : MonuAppRoute("connection")
    data object ModelSelector : MonuAppRoute("models")
    data object QuadResults : MonuAppRoute("quad_results")

    data object Settings : MonuAppRoute("settings")
    data object Profile : MonuAppRoute("profile")

    data object Notebooks : MonuAppRoute("notebooks")
    data object Voice : MonuAppRoute("voice")

    data object Library : MonuAppRoute("library")
    data object Search : MonuAppRoute("search")

    data object Favorites : MonuAppRoute("favorites")
    data object Archive : MonuAppRoute("archive")

    data object Media : MonuAppRoute("media")
    data object Notifications : MonuAppRoute("notifications")

    data object Privacy : MonuAppRoute("privacy")
    data object Security : MonuAppRoute("security")

    data object About : MonuAppRoute("about")
}
