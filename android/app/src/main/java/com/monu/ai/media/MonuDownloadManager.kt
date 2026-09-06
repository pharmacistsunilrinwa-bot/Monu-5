package com.monu.ai.media

import android.content.Context
import android.net.Uri
import java.io.File
import java.net.HttpURLConnection
import java.net.URL

data class MonuDownloadProgress(
    val downloadedBytes: Long,
    val totalBytes: Long,
    val percent: Int
)

class MonuDownloadManager(
    private val context: Context
) {

    fun download(
        url: String,
        fileName: String,
        onProgress: (MonuDownloadProgress) -> Unit
    ): Uri {

        val destination =
            File(
                context.cacheDir,
                fileName
            )

        val connection =
            URL(url)
                .openConnection() as HttpURLConnection

        connection.connect()

        val total =
            connection.contentLengthLong

        connection.inputStream.use { input ->

            destination.outputStream().use { output ->

                val buffer =
                    ByteArray(64 * 1024)

                var downloaded = 0L

                while (true) {

                    val read =
                        input.read(buffer)

                    if (read == -1) {
                        break
                    }

                    output.write(
                        buffer,
                        0,
                        read
                    )

                    downloaded += read

                    val percent =
                        if (total > 0)
                            ((downloaded * 100) / total).toInt()
                        else
                            -1

                    onProgress(
                        MonuDownloadProgress(
                            downloadedBytes = downloaded,
                            totalBytes = total,
                            percent = percent
                        )
                    )
                }
            }
        }

        connection.disconnect()

        return Uri.fromFile(destination)
    }
}
