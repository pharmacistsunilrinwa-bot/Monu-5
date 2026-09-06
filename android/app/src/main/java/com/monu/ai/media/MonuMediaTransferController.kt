package com.monu.ai.media

import android.content.Context
import android.net.Uri

enum class MonuTransferState {
    IDLE,
    PREPARING,
    UPLOADING,
    DOWNLOADING,
    COMPLETED,
    FAILED
}

data class MonuTransferStatus(
    val state: MonuTransferState,
    val progress: Int = 0,
    val message: String = ""
)

class MonuMediaTransferController(
    private val context: Context
) {

    private val mediaManager =
        MonuMediaManager(context)

    private val streamer =
        MonuChunkStreamer()

    fun prepareUpload(
        uri: Uri,
        onStatus: (MonuTransferStatus) -> Unit,
        onChunk: (MonuChunk) -> Unit
    ) {

        try {

            onStatus(
                MonuTransferStatus(
                    state =
                        MonuTransferState.PREPARING,
                    message =
                        "Preparing media"
                )
            )

            val item =
                mediaManager.inspect(uri)

            onStatus(
                MonuTransferStatus(
                    state =
                        MonuTransferState.UPLOADING,
                    message =
                        "Streaming ${item.displayName ?: "media"}"
                )
            )

            context.contentResolver
                .openInputStream(uri)
                ?.use { input ->

                    streamer.stream(
                        input
                    ) { chunk ->

                        onChunk(chunk)
                    }
                }

            onStatus(
                MonuTransferStatus(
                    state =
                        MonuTransferState.COMPLETED,
                    progress = 100,
                    message =
                        "Media stream prepared"
                )
            )

        } catch (error: Exception) {

            onStatus(
                MonuTransferStatus(
                    state =
                        MonuTransferState.FAILED,
                    message =
                        error.message
                            ?: "Media transfer failed"
                )
            )
        }
    }
}
