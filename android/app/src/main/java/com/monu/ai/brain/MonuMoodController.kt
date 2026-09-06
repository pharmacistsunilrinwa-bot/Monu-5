package com.monu.ai.brain

class MonuMoodController {

    fun detect(
        message: String
    ): MonuMood {

        val text = message.lowercase()

        return when {

            listOf(
                "urgent",
                "help",
                "emergency",
                "तुरंत",
                "जल्दी"
            ).any { it in text } ->
                MonuMood.ALERT

            listOf(
                "idea",
                "create",
                "design",
                "बनाओ",
                "आइडिया"
            ).any { it in text } ->
                MonuMood.CREATIVE

            listOf(
                "relax",
                "calm",
                "peace",
                "आराम",
                "शांत"
            ).any { it in text } ->
                MonuMood.CALM

            listOf(
                "code",
                "analyze",
                "study",
                "काम",
                "पढ़ाई"
            ).any { it in text } ->
                MonuMood.FOCUSED

            else ->
                MonuMood.NEUTRAL
        }
    }
}
