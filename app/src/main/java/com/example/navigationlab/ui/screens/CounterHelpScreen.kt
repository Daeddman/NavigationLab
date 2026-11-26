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
fun CounterHelpScreen(
    onBack: () -> Unit,
    onReturnToCounter: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize().testTag("counter_help")
            .verticalScroll(rememberScrollState()).padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text("Як працює навігація", style = MaterialTheme.typography.headlineMedium)
        Text("Шлях: Лічильник → Статистика → Довідка.")
        Text("Кнопка або системний жест «Назад» повертає на попередній підекран.")
        Button(onClick = onBack) { Text("Назад до статистики") }
        OutlinedButton(onClick = onReturnToCounter) { Text("На початок лічильника") }
    }
}
