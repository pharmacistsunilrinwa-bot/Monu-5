package com.monu.ai.navigation

enum class MonuPlusDestination(
    val destination: MonuAppRoute
) {

    FILES(MonuAppRoute.Media),
    DRIVE(MonuAppRoute.Library),
    NOTEBOOKS(MonuAppRoute.Notebooks),

    IMAGES(MonuAppRoute.Media),
    VIDEO(MonuAppRoute.Media),

    MUSIC(MonuAppRoute.Media),
    CANVAS(MonuAppRoute.Library),

    VOICE(MonuAppRoute.Voice),
    PERSONAL_INTELLIGENCE(MonuAppRoute.Dashboard)
}
