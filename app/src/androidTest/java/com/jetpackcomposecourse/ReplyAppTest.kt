package com.jetpackcomposecourse

import androidx.activity.ComponentActivity
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.platform.app.InstrumentationRegistry
import com.jetpackcomposecourse.ui.practice.replyapp.ReplyApp
import org.junit.Rule
import org.junit.Test

class ReplyAppTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun compactDevice_verifyUsingBottomNavigation() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

        // Set up compact window
        composeTestRule.setContent {
            ReplyApp(windowSize = WindowWidthSizeClass.Compact)
        }

        // Bottom navigation is displayed
        composeTestRule.onNodeWithTag(
            appContext.getString(R.string.navigation_bottom)
        ).assertExists()
    }

    @Test
    fun mediumDevice_verifyUsingNavigationRail() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

        // Set up compact window
        composeTestRule.setContent {
            ReplyApp(windowSize = WindowWidthSizeClass.Medium)
        }

        // Bottom navigation is displayed
        composeTestRule.onNodeWithTag(
            appContext.getString(R.string.navigation_rail)
        ).assertExists()
    }

    @Test
    fun expandedDevice_verifyUsingNavigationDrawer() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

        // Set up compact window
        composeTestRule.setContent {
            ReplyApp(windowSize = WindowWidthSizeClass.Expanded)
        }

        // Bottom navigation is displayed
        composeTestRule.onNodeWithTag(
            appContext.getString(R.string.navigation_drawer)
        ).assertExists()
    }
}