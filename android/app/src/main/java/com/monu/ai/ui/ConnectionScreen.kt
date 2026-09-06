package com.monu.ai.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.monu.ai.network.ConnectionDashboardState

@Composable
fun ConnectionScreen(
    state: ConnectionDashboardState,
    onCheckNow: () -> Unit,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Text(
            text = "MONU CONNECTION STATUS",
            style = MaterialTheme.typography.headlineSmall
        )

        ConnectionCard(
            title = "APK → MONU SERVER",
            value = state.apkToServer
        )

        ConnectionCard(
            title = "MONU SERVER → APK",
            value = state.serverToApk
        )

        ConnectionCard(
            title = "MESSAGE",
            value = state.message
        )

        ConnectionCard(
            title = "LATENCY",
            value = state.latency
        )

        ConnectionCard(
            title = "LAST CHECK",
            value = state.lastCheck
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            enabled = !state.checking,
            onClick = onCheckNow
        ) {
            if (state.checking) {
                CircularProgressIndicator()
                Spacer(modifier = Modifier.height(1.dp))
            } else {
                Text("CHECK CONNECTION NOW")
            }
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = onBack
        ) {
            Text("BACK")
        }
    }
}

@Composable
private fun ConnectionCard(
    title: String,
    value: String
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge
            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            Text(
                text = value,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}
