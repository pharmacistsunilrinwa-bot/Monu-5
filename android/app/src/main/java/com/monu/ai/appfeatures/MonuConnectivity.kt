package com.monu.ai.appfeatures

enum class MonuConnectivityState {
    ONLINE,
    OFFLINE,
    LIMITED,
    UNKNOWN
}

data class MonuNetworkPolicy(
    val state: MonuConnectivityState,
    val allowCloudAi: Boolean,
    val allowServer: Boolean,
    val allowDownloads: Boolean
)

class MonuConnectivityController {

    private var state =
        MonuConnectivityState.UNKNOWN

    fun update(
        connectivityState:
            MonuConnectivityState
    ) {
        state = connectivityState
    }

    fun policy(): MonuNetworkPolicy {

        return when (state) {

            MonuConnectivityState.ONLINE ->
                MonuNetworkPolicy(
                    state,
                    allowCloudAi = true,
                    allowServer = true,
                    allowDownloads = true
                )

            MonuConnectivityState.LIMITED ->
                MonuNetworkPolicy(
                    state,
                    allowCloudAi = true,
                    allowServer = false,
                    allowDownloads = false
                )

            MonuConnectivityState.OFFLINE ->
                MonuNetworkPolicy(
                    state,
                    allowCloudAi = false,
                    allowServer = false,
                    allowDownloads = false
                )

            else ->
                MonuNetworkPolicy(
                    state,
                    allowCloudAi = false,
                    allowServer = false,
                    allowDownloads = false
                )
        }
    }
}
