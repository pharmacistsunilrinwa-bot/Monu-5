package com.monu.ai.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ResponseActionBar(
    onCopy: () -> Unit,
    onVoice: () -> Unit,
    onShare: () -> Unit,
    onRegenerate: () -> Unit
) {
    Row(
        modifier =
            Modifier.fillMaxWidth(),
        horizontalArrangement =
            Arrangement.SpaceEvenly
    ) {
        Button(
            onClick = onCopy
        ) {
            Text("Copy")
        }

        Button(
            onClick = onVoice
        ) {
            Text("Voice")
        }

        Button(
            onClick = onShare
        ) {
            Text("Share")
        }

        Button(
            onClick = onRegenerate
        ) {
            Text("Change")
        }
    }
}
