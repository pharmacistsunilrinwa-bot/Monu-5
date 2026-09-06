package com.monu.ai.sync

class MonuSyncEngine(
    private val processor: MonuSyncProcessor
) {

    suspend fun synchronize(): List<MonuSyncResult> {

        val results = mutableListOf<MonuSyncResult>()

        MonuOfflineQueue.pending().forEach { request ->

            if (!MonuRecoveryPolicy.canRetry(request.attempts)) {
                return@forEach
            }

            val processing =
                request.copy(
                    status = MonuSyncStatus.PROCESSING,
                    attempts = request.attempts + 1
                )

            MonuOfflineQueue.update(processing)

            try {

                val result =
                    processor.process(processing)

                results += result

                if (result.success) {

                    MonuOfflineQueue.update(
                        processing.copy(
                            status = MonuSyncStatus.COMPLETED
                        )
                    )

                } else {

                    MonuOfflineQueue.update(
                        processing.copy(
                            status = MonuSyncStatus.FAILED
                        )
                    )
                }

            } catch (error: Exception) {

                results += MonuSyncResult(
                    requestId = request.id,
                    success = false,
                    message =
                        error.message ?: "Sync failure"
                )

                MonuOfflineQueue.update(
                    processing.copy(
                        status = MonuSyncStatus.FAILED
                    )
                )
            }
        }

        return results
    }
}
