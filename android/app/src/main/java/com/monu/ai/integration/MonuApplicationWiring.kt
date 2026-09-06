package com.monu.ai.integration

import com.monu.ai.runtime.MonuAiRuntime

object MonuApplicationWiring {

    val dispatcher =
        MonuEventDispatcher()

    val coordinator =
        MonuAppCoordinator()

    val runtime =
        MonuAiRuntime()

    val runtimeBridge =
        MonuRuntimeBridge(runtime)

    private val connectors =
        mutableListOf<MonuFeatureConnector>()

    fun register(
        connector: MonuFeatureConnector
    ) {
        connectors += connector
    }

    fun connectAll() {
        connectors.forEach {
            it.connect()
        }
    }

    fun disconnectAll() {
        connectors.forEach {
            it.disconnect()
        }
    }

    fun initialize(
        defaultModel: String
    ) {
        coordinator.initialize(
            modelId = defaultModel
        )

        connectAll()
    }
}
