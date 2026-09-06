package com.monu.ai

enum class MonuCommandType {
    CHAT,
    VOICE,
    IMAGE,
    VIDEO,
    FILE,
    CAMERA,
    CONNECTION,
    MODEL,
    MEMORY,
    UNKNOWN
}

data class MonuCommand(
    val type: MonuCommandType,
    val rawInput: String
)

class MonuCommandRouter {

    fun classify(
        input: String
    ): MonuCommand {

        val text =
            input.lowercase()

        val type =
            when {

                "connection" in text ||
                "नेटवर्क" in text ||
                "कनेक्शन" in text ->
                    MonuCommandType.CONNECTION

                "model" in text ||
                "gemini" in text ||
                "मॉडल" in text ->
                    MonuCommandType.MODEL

                "memory" in text ||
                "याद" in text ||
                "मेमोरी" in text ->
                    MonuCommandType.MEMORY

                "camera" in text ||
                "कैमरा" in text ->
                    MonuCommandType.CAMERA

                "image" in text ||
                "photo" in text ||
                "तस्वीर" in text ->
                    MonuCommandType.IMAGE

                "video" in text ||
                "वीडियो" in text ->
                    MonuCommandType.VIDEO

                "file" in text ||
                "pdf" in text ||
                "फाइल" in text ->
                    MonuCommandType.FILE

                "voice" in text ||
                "आवाज" in text ||
                "बोलो" in text ->
                    MonuCommandType.VOICE

                input.isNotBlank() ->
                    MonuCommandType.CHAT

                else ->
                    MonuCommandType.UNKNOWN
            }

        return MonuCommand(
            type = type,
            rawInput = input
        )
    }
}
