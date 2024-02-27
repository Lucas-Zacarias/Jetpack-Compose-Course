package com.tipcalculator

import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsOff
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.tipcalculator.ui.TipCalculator
import com.tipcalculator.ui.theme.TipCalculatorTheme
import org.junit.Rule
import org.junit.Test
import java.text.NumberFormat

class TipUITests {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun test_calculate_20_percent_tip() {
        composeTestRule.setContent {
            TipCalculatorTheme {
                TipCalculator()
            }
        }
        composeTestRule
            .onNodeWithText("Bill Amount")
            .performTextInput("10")
        composeTestRule
            .onNodeWithText("Tip Percentage")
            .performTextInput("20")

        val expectedTip = NumberFormat.getCurrencyInstance().format(2)
        composeTestRule
            .onNodeWithText("Tip Amount: $expectedTip")
            .assertExists("No node with this text was found.")
    }

    @Test
    fun test_calculate_20_percent_tip_with_round_up() {
        composeTestRule.setContent {
            TipCalculatorTheme {
                TipCalculator()
            }
        }
        composeTestRule
            .onNodeWithText("Bill Amount")
            .performTextInput("150")
        composeTestRule
            .onNodeWithText("Tip Percentage")
            .performTextInput("17")
        composeTestRule
            .onNodeWithTag("Switch Round Up Tip")
            .performClick()
            .assertIsEnabled()

        val expectedTip = NumberFormat.getCurrencyInstance().format(26)
        composeTestRule
            .onNodeWithText("Tip Amount: $expectedTip")
            .assertExists("No node with this text was found.")
    }

    @Test
    fun test_validate_round_up_switch_is_on() {
        composeTestRule.setContent {
            TipCalculatorTheme {
                TipCalculator()
            }
        }
        composeTestRule
            .onNodeWithTag("Switch Round Up Tip")
            .performClick()
            .assertIsEnabled()
    }

    @Test
    fun test_validate_round_up_switch_is_off() {
        composeTestRule.setContent {
            TipCalculatorTheme {
                TipCalculator()
            }
        }
        composeTestRule
            .onNodeWithTag("Switch Round Up Tip")
            .assertIsOff()
    }

}