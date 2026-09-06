package com.monu.ai.sync

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MonuConnectivityRecovery {

    private val _networkState =
        MutableStateFlow(
            MonuNetworkSnapshot(
                MonuNetworkState.UNKNOWN
            )
        )

    val networkState: StateFlow<MonuNetworkSnapshot> =
        _networkState

    fun update(
        state: MonuNetworkState,
        detail: String? = null
    ) {
        _networkState.value =
            MonuNetworkSnapshot(
                state = state,
                detail = detail
            )
    }

    fun isOnline(): Boolean {
        return _networkState.value.state ==
            MonuNetworkState.ONLINE
    }
}
