package com.monu.ai.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.monu.ai.network.MonuModel
import com.monu.ai.network.MonuModelRegistry

@Composable
fun ModelSelectorScreen(
    selectedModel: String,
    onSelect: (String) -> Unit,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "SELECT AI MODEL",
            style = MaterialTheme.typography.headlineSmall
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(
                MonuModelRegistry.models
            ) { model ->

                ModelRow(
                    model = model,
                    selected =
                        model.id == selectedModel,
                    onSelect = onSelect
                )
            }
        }
    }
}

@Composable
private fun ModelRow(
    model: MonuModel,
    selected: Boolean,
    onSelect: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable {
                onSelect(model.id)
            }
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment =
                Alignment.CenterVertically
        ) {
            RadioButton(
                selected = selected,
                onClick = {
                    onSelect(model.id)
                }
            )

            Column(
                modifier = Modifier.padding(start = 10.dp)
            ) {
                Text(
                    text = model.label,
                    style =
                        MaterialTheme.typography.titleMedium
                )

                Text(
                    text = model.id,
                    style =
                        MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}
