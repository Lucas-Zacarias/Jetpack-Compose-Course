package com.jetpackcomposecourse

import androidx.activity.ComponentActivity
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.jetpackcomposecourse.ui.practice.replyapp.ReplyApp
import org.junit.Rule
import org.junit.Test

class ReplyAppTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun compactDevice_verifyUsingBottomNavigation() {
        // Set up compact window
        composeTestRule.setContent {
            ReplyApp(windowSize = WindowWidthSizeClass.Compact)
        }

        // Bottom navigation is displayed
        composeTestRule.onNodeWithTag(
            composeTestRule.activity.getString(R.string.navigation_bottom)
        ).assertExists()
    }

    @Test
    fun mediumDevice_verifyUsingNavigationRail() {
        // Set up compact window
        composeTestRule.setContent {
            ReplyApp(windowSize = WindowWidthSizeClass.Medium)
        }

        // Bottom navigation is displayed
        composeTestRule.onNodeWithTag(
            composeTestRule.activity.getString(R.string.navigation_rail)
        ).assertExists()
    }

    @Test
    fun expandedDevice_verifyUsingNavigationDrawer() {
        // Set up compact window
        composeTestRule.setContent {
            ReplyApp(windowSize = WindowWidthSizeClass.Expanded)
        }

        // Bottom navigation is displayed
        composeTestRule.onNodeWithTag(
            composeTestRule.activity.getString(R.string.navigation_drawer)
        ).assertExists()
    }
}