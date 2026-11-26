package com.example.navigationlab.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.navigationlab.navigation.AppDestinations
import com.example.navigationlab.navigation.CounterNavigationGraph
import com.example.navigationlab.ui.screens.CounterNavigationScreen
import com.example.navigationlab.ui.screens.MessageScreen
import com.example.navigationlab.ui.screens.ScoreScreen
import com.example.navigationlab.viewmodel.LabViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationLabApp(model: LabViewModel) {
    val state by model.state.collectAsStateWithLifecycle()

    // Навігація належить UI; дані та обробка кнопок залишаються у ViewModel.
    // Контролер створюємо над when, щоб не втрачати маршрут при зміні вкладки.
    val counterNavController = rememberNavController()
    val backStackEntry by counterNavController.currentBackStackEntryAsState()
    val isCounter = state.destination == AppDestinations.COUNTER
    val canGoBack = isCounter && counterNavController.previousBackStackEntry != null
    val title = if (isCounter) {
        when (backStackEntry?.destination?.route) {
            CounterNavigationGraph.Details.route -> CounterNavigationGraph.Details.title
            CounterNavigationGraph.Help.route -> CounterNavigationGraph.Help.title
            else -> CounterNavigationGraph.Main.title
        }
    } else {
        state.destination.label
    }

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            AppDestinations.entries.forEach { destination ->
                item(
                    icon = { Text(destination.symbol, fontSize = 22.sp) },
                    label = { Text(destination.label) },
                    selected = state.destination == destination,
                    onClick = { model.selectDestination(destination) },
                )
            }
        },
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = { Text(title) },
                    navigationIcon = {
                        if (canGoBack) {
                            TextButton(onClick = { counterNavController.navigateUp() }) {
                                Text("Назад")
                            }
                        }
                    },
                )
            },
        ) { innerPadding ->
            val screenModifier = Modifier.padding(innerPadding)
            when (state.destination) {
                AppDestinations.COUNTER -> CounterNavigationScreen(
                    navController = counterNavController,
                    count = state.count,
                    onAdd = model::incrementCounter,
                    modifier = screenModifier,
                )
                AppDestinations.MESSAGE -> MessageScreen(
                    changed = state.messageChanged,
                    libraryItems = state.libraryItems,
                    onChange = model::toggleMessage,
                    modifier = screenModifier,
                )
                AppDestinations.SCORE -> ScoreScreen(
                    score = state.score,
                    onAdd = model::addPoints,
                    modifier = screenModifier,
                )
            }
        }
    }
}
