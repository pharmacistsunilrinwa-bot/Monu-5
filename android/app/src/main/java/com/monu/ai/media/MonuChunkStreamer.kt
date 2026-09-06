package com.monu.ai.media

import java.io.InputStream

data class MonuChunk(
    val index: Long,
    val totalBytesRead: Long,
    val bytes: ByteArray,
    val isLast: Boolean
)

class MonuChunkStreamer(
    private val chunkSize: Int = 512 * 1024
) {

    fun stream(
        input: InputStream,
        onChunk: (MonuChunk) -> Unit
    ) {

        var index = 0L
        var total = 0L

        val buffer =
            ByteArray(chunkSize)

        while (true) {

            val read =
                input.read(buffer)

            if (read <= 0) {
                break
            }

            total += read

            val data =
                buffer.copyOf(read)

            onChunk(
                MonuChunk(
                    index = index,
                    totalBytesRead = total,
                    bytes = data,
                    isLast = false
                )
            )

            index++
        }

        onChunk(
            MonuChunk(
                index = index,
                totalBytesRead = total,
                bytes = ByteArray(0),
                isLast = true
            )
        )
    }
}
