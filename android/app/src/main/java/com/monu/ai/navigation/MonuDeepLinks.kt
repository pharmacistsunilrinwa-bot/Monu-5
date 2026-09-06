package com.monu.ai.navigation

data class MonuDeepLink(
    val path: String,
    val destination: String,
    val requiresAuthentication: Boolean = true
)

object MonuDeepLinks {

    const val SCHEME = "monu"
    const val HOST = "ai"

    val routes = listOf(
        MonuDeepLink("/", "home"),
        MonuDeepLink("/chat", "chat"),
        MonuDeepLink("/new-chat", "new_chat"),
        MonuDeepLink("/settings", "settings"),
        MonuDeepLink("/profile", "profile"),
        MonuDeepLink("/notebooks", "notebooks"),
        MonuDeepLink("/library", "library"),
        MonuDeepLink("/connections", "connections"),
        MonuDeepLink("/models", "models")
    )

    fun resolve(uri: String): MonuDeepLink? {
        return routes.firstOrNull { route ->
            uri.contains(route.path)
        }
    }
}
