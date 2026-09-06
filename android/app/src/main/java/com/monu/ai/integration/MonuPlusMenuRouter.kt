package com.monu.ai.integration

sealed class MonuPlusAction {

    data object Files :
        MonuPlusAction()

    data object Photos :
        MonuPlusAction()

    data object Camera :
        MonuPlusAction()

    data object Notebook :
        MonuPlusAction()

    data object Image :
        MonuPlusAction()

    data object Video :
        MonuPlusAction()

    data object Music :
        MonuPlusAction()

    data object Canvas :
        MonuPlusAction()
}

object MonuPlusMenuRouter {

    fun parse(
        value: String
    ): MonuPlusAction? {

        return when (
            value.lowercase()
        ) {

            "files" ->
                MonuPlusAction.Files

            "photos" ->
                MonuPlusAction.Photos

            "camera" ->
                MonuPlusAction.Camera

            "notebook" ->
                MonuPlusAction.Notebook

            "image" ->
                MonuPlusAction.Image

            "video" ->
                MonuPlusAction.Video

            "music" ->
                MonuPlusAction.Music

            "canvas" ->
                MonuPlusAction.Canvas

            else -> null
        }
    }
}
