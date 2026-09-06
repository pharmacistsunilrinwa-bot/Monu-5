package com.monu.ai.ui.premium

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MonuOnboardingScreen(
    onComplete: () -> Unit
) {
    var page by remember {
        mutableStateOf(0)
    }

    val pages = listOf(
        "Welcome to MONU" to
            "Your personal AI assistant.",

        "Personal Intelligence" to
            "Chat, voice, files, media and memory in one application.",

        "Your Control" to
            "You control permissions, privacy, memory and connectivity."
    )

    Scaffold { padding ->

        Column(
            modifier =
                Modifier
                    .padding(padding)
                    .padding(24.dp)
                    .fillMaxSize(),

            horizontalAlignment =
                Alignment.CenterHorizontally,

            verticalArrangement =
                Arrangement.Center
        ) {

            Text(
                pages[page].first,
                style =
                    MaterialTheme
                        .typography
                        .headlineMedium
            )

            Spacer(
                modifier =
                    Modifier.height(20.dp)
            )

            Text(
                pages[page].second
            )

            Spacer(
                modifier =
                    Modifier.height(40.dp)
            )

            Button(
                onClick = {

                    if (
                        page <
                        pages.lastIndex
                    ) {
                        page++
                    } else {
                        onComplete()
                    }
                }
            ) {

                Text(
                    if (
                        page ==
                        pages.lastIndex
                    )
                        "Start MONU"
                    else
                        "Continue"
                )
            }
        }
    }
}
