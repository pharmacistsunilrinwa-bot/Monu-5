package com.monu.ai.ui

enum class MonuScreen {
    CHAT,
    CONNECTION,
    MODELS,
    QUAD_RESULTS,
    MEMORY,
    SETTINGS
}

class MonuDashboardController {

    var currentScreen: MonuScreen =
        MonuScreen.CHAT
        private set

    fun openChat() {
        currentScreen =
            MonuScreen.CHAT
    }

    fun openConnection() {
        currentScreen =
            MonuScreen.CONNECTION
    }

    fun openModels() {
        currentScreen =
            MonuScreen.MODELS
    }

    fun openQuadResults() {
        currentScreen =
            MonuScreen.QUAD_RESULTS
    }

    fun openMemory() {
        currentScreen =
            MonuScreen.MEMORY
    }

    fun openSettings() {
        currentScreen =
            MonuScreen.SETTINGS
    }
}
