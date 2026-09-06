package com.monu.ai.navigation

data class MonuNavigationState(
    val currentRoute: String = MonuAppRoute.Chat.route,
    val previousRoutes: List<String> = emptyList()
)
