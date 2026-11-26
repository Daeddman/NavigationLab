package com.example.navigationlab

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.espresso.Espresso.pressBack
import org.junit.Rule
import org.junit.Test

class NavigationFlowTest {
    @get:Rule
    val compose = createAndroidComposeRule<MainActivity>()

    @Test
    fun twoForwardTransitionsAndSystemBackPreserveCounter() {
        repeat(2) { compose.onNodeWithText("Додати натискання").performClick() }
        compose.onNodeWithText("Відкрити статистику").performClick()
        compose.onNodeWithTag("counter_details").assertIsDisplayed()
        compose.onNodeWithText("Усього натискань: 2").assertIsDisplayed()
        compose.onNodeWithText("Відкрити довідку").performClick()
        compose.onNodeWithTag("counter_help").assertIsDisplayed()
        pressBack()
        compose.onNodeWithTag("counter_details").assertIsDisplayed()
        pressBack()
        compose.onNodeWithTag("counter_main").assertIsDisplayed()
        compose.onNodeWithText("Натискань: 2").assertIsDisplayed()
    }

    @Test
    fun recreationKeepsRouteAndViewModelData() {
        compose.onNodeWithText("Додати натискання").performClick()
        compose.onNodeWithText("Відкрити статистику").performClick()
        compose.activityRule.scenario.recreate()
        compose.onNodeWithTag("counter_details").assertIsDisplayed()
        compose.onNodeWithText("Усього натискань: 1").assertIsDisplayed()
        compose.onNodeWithText("Назад").performClick()
        compose.onNodeWithText("Натискань: 1").assertIsDisplayed()
    }

    @Test
    fun tabSwitchPreservesCounterRouteAndReturnToRootDoesNotDuplicateIt() {
        compose.onNodeWithText("Відкрити статистику").performClick()
        compose.onNodeWithText("Відкрити довідку").performClick()
        compose.onNodeWithText("Повідомлення").performClick()
        compose.onNodeWithText("Змінити повідомлення").assertIsDisplayed()
        compose.onNodeWithText("Лічильник").performClick()
        compose.onNodeWithTag("counter_help").assertIsDisplayed()
        compose.onNodeWithText("На початок лічильника").performClick()
        compose.onNodeWithTag("counter_main").assertIsDisplayed()
        compose.onNodeWithText("Відкрити статистику").performClick()
        compose.onNodeWithText("Назад до лічильника").performClick()
        compose.onNodeWithTag("counter_main").assertIsDisplayed()
    }
}
