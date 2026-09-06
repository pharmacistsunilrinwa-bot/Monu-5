package com.monu.ai.persistence

import android.content.Context

object MonuStorageProvider {

    @Volatile
    private var persistence:
        MonuPersistenceManager? = null

    fun initialize(
        context: Context
    ) {

        if (persistence == null) {

            synchronized(this) {

                if (persistence == null) {

                    persistence =
                        MonuPersistenceManager(
                            context.applicationContext
                        )
                }
            }
        }
    }

    fun get(): MonuPersistenceManager {

        return persistence
            ?: error(
                "MonuStorageProvider not initialized"
            )
    }
}
