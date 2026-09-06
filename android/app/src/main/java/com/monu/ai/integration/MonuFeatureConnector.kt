package com.monu.ai.integration

interface MonuFeatureConnector {

    val featureName: String

    fun connect()

    fun disconnect()
}
