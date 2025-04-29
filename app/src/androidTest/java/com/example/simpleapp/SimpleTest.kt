package com.example.simpleapp

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.espresso.Espresso
import com.example.simpleapp.presentation.navigation.AppNavigation
import org.junit.Rule
import org.junit.Test

class SimpleTest {


    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testFlow() {
        composeTestRule.setContent {
            AppNavigation()
        }

        val item = "Car"
        val backButton = composeTestRule.onNodeWithTag("back")
        val list = getListAndExecuteThings()

        clickOnListItem( item = item)
        backButton.assertExists()
        testElements(item = item)

        backButton.performClick()
        Espresso.pressBack()
        composeTestRule.waitForIdle()

        list.assertExists()
    }

    private fun clickOnListItem(item: String) {
        composeTestRule.onNodeWithTag("item_${item}").run {
            assertExists()
            performClick()
        }
    }

    private fun testElements(item: String) {
        composeTestRule.onNodeWithTag("title_${item}").run {
            assertExists()
            assertTextEquals(item)
        }
        composeTestRule.onNodeWithTag("description_${item}").run {
            assertExists()
            assertTextEquals("This is a Car")
        }
    }

    private fun getListAndExecuteThings() = composeTestRule.onNodeWithTag("toTheListButton").also {
        it.assertExists()
        it.performClick()
        it.assertDoesNotExist()

    }
}