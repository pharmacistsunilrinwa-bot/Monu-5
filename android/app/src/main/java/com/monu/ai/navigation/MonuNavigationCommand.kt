package com.monu.ai.navigation

sealed interface MonuNavigationCommand {

    data class Navigate(
        val route: MonuAppRoute
    ) : MonuNavigationCommand

    data object Back : MonuNavigationCommand

    data object Home : MonuNavigationCommand

    data class DeepLink(
        val value: String
    ) : MonuNavigationCommand
}
