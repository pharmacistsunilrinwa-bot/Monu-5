package com.monu.ai.navigation

sealed class MonuDestination(
    val route: String
) {

    data object Chat :
        MonuDestination("chat")

    data object Connection :
        MonuDestination("connection")

    data object Models :
        MonuDestination("models")

    data object QuadRoutes :
        MonuDestination("quad_routes")

    data object Images :
        MonuDestination("images")

    data object Videos :
        MonuDestination("videos")

    data object Library :
        MonuDestination("library")

    data object Notebooks :
        MonuDestination("notebooks")

    data object Files :
        MonuDestination("files")

    data object Music :
        MonuDestination("music")

    data object Canvas :
        MonuDestination("canvas")

    data object GuidedLearning :
        MonuDestination("guided_learning")

    data object PersonalIntelligence :
        MonuDestination("personal_intelligence")

    data object Settings :
        MonuDestination("settings")
}

object MonuNavigationRegistry {

    val all = listOf(
        MonuDestination.Chat,
        MonuDestination.Connection,
        MonuDestination.Models,
        MonuDestination.QuadRoutes,
        MonuDestination.Images,
        MonuDestination.Videos,
        MonuDestination.Library,
        MonuDestination.Notebooks,
        MonuDestination.Files,
        MonuDestination.Music,
        MonuDestination.Canvas,
        MonuDestination.GuidedLearning,
        MonuDestination.PersonalIntelligence,
        MonuDestination.Settings
    )
}
