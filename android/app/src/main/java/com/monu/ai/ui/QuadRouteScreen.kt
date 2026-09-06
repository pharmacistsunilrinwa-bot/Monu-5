package com.monu.ai.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.monu.ai.network.RouteResult

@Composable
fun QuadRouteScreen(
    results: List<RouteResult>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "MONU QUAD ROUTING",
            style =
                MaterialTheme.typography.headlineSmall
        )

        LazyColumn {
            items(results) { result ->

                RouteResultCard(
                    result
                )
            }
        }
    }
}

@Composable
private fun RouteResultCard(
    result: RouteResult
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(
            modifier =
                Modifier.padding(16.dp)
        ) {
            Text(
                text =
                    result.route.uppercase(),
                style =
                    MaterialTheme.typography.titleMedium
            )

            Text(
                text =
                    if (result.success)
                        "STATUS: SUCCESS"
                    else
                        "STATUS: FAILED"
            )

            Text(
                text =
                    "LATENCY: ${
                        result.latencyMs
                            ?: "--"
                    } ms"
            )

            Text(
                text = result.response
            )
        }
    }
}
