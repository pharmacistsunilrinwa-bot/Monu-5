@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.monu.ai.ui.premium

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.monu.ai.notebooks.MonuNotebook
import java.util.UUID

@Composable
fun MonuNotebookScreen(
    onBack: () -> Unit
) {
    val notebooks =
        remember {
            mutableStateListOf<MonuNotebook>()
        }

    var showCreate by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Notebooks") },
                navigationIcon = {
                    TextButton(onClick = onBack) {
                        Text("Back")
                    }
                },
                actions = {
                    TextButton(
                        onClick = {
                            showCreate = true
                        }
                    ) {
                        Text("New")
                    }
                }
            )
        }
    ) { padding ->

        LazyColumn(
            modifier =
                Modifier
                    .padding(padding)
                    .fillMaxSize()
        ) {

            items(notebooks) {
                ListItem(
                    headlineContent = {
                        Text(it.title)
                    },
                    supportingContent = {
                        Text(
                            it.content
                                .take(100)
                                .ifBlank {
                                    "Empty notebook"
                                }
                        )
                    }
                )

                HorizontalDivider()
            }
        }
    }

    if (showCreate) {

        var title by remember {
            mutableStateOf("")
        }

        AlertDialog(
            onDismissRequest = {
                showCreate = false
            },

            title = {
                Text("New Notebook")
            },

            text = {

                OutlinedTextField(
                    value = title,
                    onValueChange = {
                        title = it
                    },
                    label = {
                        Text("Notebook Name")
                    }
                )
            },

            confirmButton = {

                TextButton(
                    onClick = {

                        if (title.isNotBlank()) {

                            notebooks.add(
                                MonuNotebook(
                                    id =
                                        UUID
                                            .randomUUID()
                                            .toString(),

                                    title = title
                                )
                            )

                            showCreate = false
                        }
                    }
                ) {
                    Text("Create")
                }
            }
        )
    }
}
