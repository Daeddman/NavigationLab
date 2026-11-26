package com.example.navigationlab.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.navigationlab.navigation.CounterNavigationGraph

@Composable
fun CounterNavigationScreen(
    navController: NavHostController,
    count: Int,
    onAdd: () -> Unit,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = CounterNavigationGraph.Main.route,
        modifier = modifier,
    ) {
        composable(CounterNavigationGraph.Main.route) {
            CounterScreen(
                count = count,
                onAdd = onAdd,
                onNavigateToDetails = {
                    navController.navigate(CounterNavigationGraph.Details.route) {
                        launchSingleTop = true
                    }
                },
                modifier = Modifier,
            )
        }
        composable(CounterNavigationGraph.Details.route) {
            CounterDetailsScreen(
                count = count,
                onNavigateToHelp = {
                    navController.navigate(CounterNavigationGraph.Help.route) {
                        launchSingleTop = true
                    }
                },
                onBack = { navController.popBackStack() },
            )
        }
        composable(CounterNavigationGraph.Help.route) {
            CounterHelpScreen(
                onBack = { navController.popBackStack() },
                onReturnToCounter = {
                    navController.popBackStack(
                        route = CounterNavigationGraph.Main.route,
                        inclusive = false,
                    )
                },
            )
        }
    }
}
