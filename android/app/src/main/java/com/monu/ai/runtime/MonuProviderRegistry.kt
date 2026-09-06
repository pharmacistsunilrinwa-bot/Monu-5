package com.monu.ai.runtime

import com.monu.ai.runtime.providers.MonuAiProvider
import java.util.concurrent.ConcurrentHashMap

object MonuProviderRegistry {

    private val providers =
        ConcurrentHashMap<String, MonuAiProvider>()

    fun register(provider: MonuAiProvider) {
        providers[provider.providerName] = provider
    }

    fun get(name: String): MonuAiProvider? {
        return providers[name]
    }

    fun available(): List<String> {
        return providers.keys().toList()
    }

    fun clear() {
        providers.clear()
    }
}
