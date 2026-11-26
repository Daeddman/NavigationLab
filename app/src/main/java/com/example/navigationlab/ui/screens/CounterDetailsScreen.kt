package com.example.navigationlab.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp

@Composable
fun CounterDetailsScreen(
    count: Int,
    onNavigateToHelp: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize().testTag("counter_details")
            .verticalScroll(rememberScrollState()).padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text("Статистика лічильника", style = MaterialTheme.typography.headlineMedium)
        Text("Усього натискань: $count")
        Text("Значення надходить із тієї самої ViewModel, що й на першому підекрані.")
        Button(onClick = onNavigateToHelp) { Text("Відкрити довідку") }
        OutlinedButton(onClick = onBack) { Text("Назад до лічильника") }
    }
}
