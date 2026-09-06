package com.monu.ai.navigation

enum class MonuDrawerDestination(
    val destination: MonuAppRoute
) {

    NEW_CHAT(MonuAppRoute.Chat),
    SEARCH(MonuAppRoute.Search),

    LIBRARY(MonuAppRoute.Library),
    NOTEBOOKS(MonuAppRoute.Notebooks),

    FAVORITES(MonuAppRoute.Favorites),
    ARCHIVE(MonuAppRoute.Archive),

    SETTINGS(MonuAppRoute.Settings),
    PROFILE(MonuAppRoute.Profile)
}
