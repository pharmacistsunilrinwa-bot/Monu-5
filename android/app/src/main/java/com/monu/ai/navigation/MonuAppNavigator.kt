package com.monu.ai.navigation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MonuAppNavigator {

    private val _state =
        MutableStateFlow(MonuNavigationState())

    val state: StateFlow<MonuNavigationState> =
        _state

    fun navigate(route: MonuAppRoute) {

        val current = _state.value

        _state.value =
            current.copy(
                currentRoute = route.route,
                previousRoutes =
                    current.previousRoutes +
                        current.currentRoute
            )
    }

    fun back() {

        val current = _state.value

        if (current.previousRoutes.isEmpty()) {
            return
        }

        val previous =
            current.previousRoutes.last()

        _state.value =
            current.copy(
                currentRoute = previous,
                previousRoutes =
                    current.previousRoutes.dropLast(1)
            )
    }

    fun home() {

        _state.value =
            MonuNavigationState(
                currentRoute =
                    MonuAppRoute.Chat.route
            )
    }
}
