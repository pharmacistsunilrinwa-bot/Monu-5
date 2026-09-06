package com.monu.ai.ui.premium

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.monu.ai.settings.MonuFontSize
import com.monu.ai.settings.MonuThemeMode

@Composable
fun MonuSettingsScreen(
    onBack: () -> Unit
) {
    var theme by remember {
        mutableStateOf(MonuThemeMode.SYSTEM)
    }

    var fontSize by remember {
        mutableStateOf(MonuFontSize.NORMAL)
    }

    var voiceEnabled by remember {
        mutableStateOf(true)
    }

    var notificationsEnabled by remember {
        mutableStateOf(true)
    }

    var dynamicTheme by remember {
        mutableStateOf(true)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings") },
                navigationIcon = {
                    TextButton(onClick = onBack) {
                        Text("Back")
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(
                    rememberScrollState()
                ),
            verticalArrangement =
                Arrangement.spacedBy(12.dp)
        ) {

            Text(
                "Appearance",
                style =
                    MaterialTheme
                        .typography
                        .titleMedium
            )

            Text("Theme")

            Row(
                horizontalArrangement =
                    Arrangement.spacedBy(8.dp)
            ) {
                MonuThemeMode.entries.forEach {
                    FilterChip(
                        selected = theme == it,
                        onClick = { theme = it },
                        label = {
                            Text(it.name)
                        }
                    )
                }
            }

            Text("Font Size")

            Row(
                horizontalArrangement =
                    Arrangement.spacedBy(8.dp)
            ) {
                MonuFontSize.entries.forEach {
                    FilterChip(
                        selected = fontSize == it,
                        onClick = { fontSize = it },
                        label = {
                            Text(it.name)
                        }
                    )
                }
            }

            HorizontalDivider()

            Text(
                "AI Interaction",
                style =
                    MaterialTheme
                        .typography
                        .titleMedium
            )

            SettingSwitch(
                "Voice Features",
                voiceEnabled
            ) {
                voiceEnabled = it
            }

            SettingSwitch(
                "Notifications",
                notificationsEnabled
            ) {
                notificationsEnabled = it
            }

            SettingSwitch(
                "Dynamic Theme",
                dynamicTheme
            ) {
                dynamicTheme = it
            }

            HorizontalDivider()

            Text(
                "Data & Privacy",
                style =
                    MaterialTheme
                        .typography
                        .titleMedium
            )

            ListItem(
                headlineContent = {
                    Text("Temporary Chat")
                },
                supportingContent = {
                    Text(
                        "Start conversations without permanent memory"
                    )
                }
            )

            ListItem(
                headlineContent = {
                    Text("Backup & Restore")
                },
                supportingContent = {
                    Text(
                        "Export or restore MONU data"
                    )
                }
            )

            ListItem(
                headlineContent = {
                    Text("About MONU")
                },
                supportingContent = {
                    Text(
                        "Personal AI application"
                    )
                }
            )
        }
    }
}

@Composable
private fun SettingSwitch(
    title: String,
    checked: Boolean,
    onChange: (Boolean) -> Unit
) {
    Row(
        modifier =
            Modifier.fillMaxWidth(),
        horizontalArrangement =
            Arrangement.SpaceBetween
    ) {
        Text(title)

        Switch(
            checked = checked,
            onCheckedChange = onChange
        )
    }
}
