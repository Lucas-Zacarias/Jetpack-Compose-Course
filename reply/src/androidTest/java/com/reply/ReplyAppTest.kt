package com.reply

import androidx.activity.ComponentActivity
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Rule
import org.junit.Test

class ReplyAppTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    @TestCompactWidth
    fun compactDevice_verifyUsingBottomNavigation() {
        // Set up compact window
        composeTestRule.setContent {
            com.reply.ui.ReplyApp(windowSize = WindowWidthSizeClass.Compact)
        }

        // Bottom navigation is displayed
        composeTestRule.onNodeWithTag(
            composeTestRule.activity.getString(R.string.navigation_bottom)
        ).assertExists()
    }

    @Test
    @TestMediumWidth
    fun mediumDevice_verifyUsingNavigationRail() {
        // Set up compact window
        composeTestRule.setContent {
            com.reply.ui.ReplyApp(windowSize = WindowWidthSizeClass.Medium)
        }

        // Bottom navigation is displayed
        composeTestRule.onNodeWithTag(
            composeTestRule.activity.getString(R.string.navigation_rail)
        ).assertExists()
    }

    @Test
    @TestExpandedWidth
    fun expandedDevice_verifyUsingNavigationDrawer() {
        // Set up compact window
        composeTestRule.setContent {
            com.reply.ui.ReplyApp(windowSize = WindowWidthSizeClass.Expanded)
        }

        // Bottom navigation is displayed
        composeTestRule.onNodeWithTag(
            composeTestRule.activity.getString(R.string.navigation_drawer)
        ).assertExists()
    }
}