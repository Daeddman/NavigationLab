package com.example.navigationlab

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import androidx.compose.ui.test.performScrollToNode
import androidx.compose.ui.test.hasText
import org.junit.Rule
import org.junit.Test

class LibraryListTest {
    @get:Rule
    val compose = createAndroidComposeRule<MainActivity>()

    @Test
    fun mixedListScrollsAndMessageButtonStaysVisible() {
        compose.onNodeWithText("Повідомлення").performClick()
        compose.onNodeWithTag("library_list").assertIsDisplayed()
        compose.onNodeWithText("Лісова пісня").assertIsDisplayed()
        compose.onNodeWithTag("library_list").performScrollToNode(hasText("Царівна"))
        compose.onNodeWithText("Царівна").assertIsDisplayed()
        compose.onNodeWithText("Змінити повідомлення").performClick()
        compose.onNodeWithText("Гарного дня!").assertIsDisplayed()
        compose.activityRule.scenario.recreate()
        compose.onNodeWithText("Гарного дня!").assertIsDisplayed()
        compose.onNodeWithText("Царівна").assertIsDisplayed()
    }

    @Test
    fun horizontalListInsideVerticalListScrollsIndependently() {
        compose.onNodeWithText("Повідомлення").performClick()
        compose.onNodeWithTag("library_list").performScrollToIndex(2)
        compose.onNodeWithTag("authors_row_row_featured").performScrollToIndex(4)
        compose.onNodeWithText("Ольга Кобилянська").assertIsDisplayed()
        compose.onNodeWithTag("library_list").performScrollToNode(hasText("Царівна"))
        compose.onNodeWithText("Царівна").assertIsDisplayed()
    }
}
