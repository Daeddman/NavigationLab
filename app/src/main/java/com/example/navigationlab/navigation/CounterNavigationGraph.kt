package com.example.navigationlab.navigation

// Як у FavoriteNavigationGraph з прикладу oboris/NewNav2025.
sealed class CounterNavigationGraph(val route: String, val title: String) {
    object Main : CounterNavigationGraph("counter_main", "Лічильник")
    object Details : CounterNavigationGraph("counter_details", "Статистика")
    object Help : CounterNavigationGraph("counter_help", "Довідка")
}
