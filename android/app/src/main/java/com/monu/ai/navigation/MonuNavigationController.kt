package com.monu.ai.navigation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object MonuNavigationController {

    private val backStack =
        mutableListOf<MonuAppRoute>()

    private val _currentRoute =
        MutableStateFlow<MonuAppRoute>(
            MonuAppRoute.Chat
        )

    val currentRoute: StateFlow<MonuAppRoute> =
        _currentRoute.asStateFlow()

    fun navigate(
        destination: MonuAppRoute,
        saveCurrent: Boolean = true
    ) {
        if (saveCurrent &&
            _currentRoute.value.route != destination.route
        ) {
            backStack.add(_currentRoute.value)
        }

        _currentRoute.value = destination
    }

    fun back(): Boolean {

        val previous =
            backStack.removeLastOrNull()
                ?: return false

        _currentRoute.value = previous

        return true
    }

    fun reset() {

        backStack.clear()

        _currentRoute.value =
            MonuAppRoute.Chat
    }

    fun history(): List<String> {

        return backStack.map {
            it.route
        }
    }
}
