package com.monu.ai.system

import android.content.Context
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MonuCrashHandler(
    private val context: Context
) : Thread.UncaughtExceptionHandler {

    private val previousHandler =
        Thread.getDefaultUncaughtExceptionHandler()

    override fun uncaughtException(
        thread: Thread,
        throwable: Throwable
    ) {

        try {

            val directory =
                File(
                    context.filesDir,
                    "monu_crash_logs"
                )

            if (!directory.exists()) {
                directory.mkdirs()
            }

            val timestamp =
                SimpleDateFormat(
                    "yyyy-MM-dd_HH-mm-ss",
                    Locale.US
                ).format(Date())

            val file =
                File(
                    directory,
                    "crash_$timestamp.log"
                )

            file.writeText(
                buildString {
                    appendLine(
                        "MONU CRASH REPORT"
                    )

                    appendLine(
                        "Thread: ${thread.name}"
                    )

                    appendLine(
                        "Message: ${throwable.message}"
                    )

                    appendLine()

                    appendLine(
                        throwable.stackTraceToString()
                    )
                }
            )

        } catch (_: Exception) {
        }

        previousHandler?.uncaughtException(
            thread,
            throwable
        )
    }

    companion object {

        fun install(
            context: Context
        ) {

            Thread.setDefaultUncaughtExceptionHandler(
                MonuCrashHandler(context)
            )
        }
    }
}
