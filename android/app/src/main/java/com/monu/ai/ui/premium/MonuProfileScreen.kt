package com.monu.ai.ui.premium

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MonuProfileScreen(
    onBack: () -> Unit
) {
    var ownerName by remember {
        mutableStateOf("Sunil Rinwa")
    }

    var aiName by remember {
        mutableStateOf("MONU")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Personal Intelligence")
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
                    .padding(20.dp)
                    .fillMaxSize(),
            verticalArrangement =
                Arrangement.spacedBy(16.dp)
        ) {

            Text(
                "Owner Profile",
                style =
                    MaterialTheme
                        .typography
                        .headlineSmall
            )

            OutlinedTextField(
                value = ownerName,
                onValueChange = {
                    ownerName = it
                },
                label = {
                    Text("Owner Name")
                },
                modifier =
                    Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = aiName,
                onValueChange = {
                    aiName = it
                },
                label = {
                    Text("AI Name")
                },
                modifier =
                    Modifier.fillMaxWidth()
            )

            Text(
                "MONU uses your local preferences and configured memory to personalize the experience."
            )

            Button(
                onClick = {},
                modifier =
                    Modifier.fillMaxWidth()
            ) {
                Text("Save Profile")
            }
        }
    }
}
