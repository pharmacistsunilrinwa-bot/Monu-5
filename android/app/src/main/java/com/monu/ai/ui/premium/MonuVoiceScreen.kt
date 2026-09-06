@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.monu.ai.ui.premium

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MonuVoiceScreen(
    onBack: () -> Unit
) {
    var listening by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Voice Conversation")
                },
                navigationIcon = {
                    TextButton(onClick = onBack) {
                        Text("Back")
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier =
                Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .padding(24.dp),

            horizontalAlignment =
                Alignment.CenterHorizontally,

            verticalArrangement =
                Arrangement.Center
        ) {

            Text(
                if (listening)
                    "MONU is listening..."
                else
                    "Tap to speak",

                style =
                    MaterialTheme
                        .typography
                        .headlineSmall
            )

            Spacer(
                modifier =
                    Modifier.height(30.dp)
            )

            Button(
                onClick = {
                    listening = !listening
                },
                modifier =
                    Modifier.size(
                        width = 180.dp,
                        height = 70.dp
                    )
            ) {

                Text(
                    if (listening)
                        "Stop"
                    else
                        "Start Voice"
                )
            }

            Spacer(
                modifier =
                    Modifier.height(30.dp)
            )

            Text(
                "Voice commands are processed through the MONU voice engine."
            )
        }
    }
}
