package com.cupcake.test

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.cupcake.ui.OrderSummaryScreen
import com.cupcake.ui.SelectOptionScreen
import com.cupcake.ui.StartOrderScreen
import com.cupcake.R
import com.cupcake.ui.CupcakeOrderUiState
import org.junit.Rule
import org.junit.Test

class CupcakeOrderScreenTest {
    /*
    * Note: Test method names are different from the function names you use in app code.
    * A good naming convention for test methods is the following:
    * thingUnderTest_TriggerOfTest_ResultOfTest
    */
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun selectOptionScreen_verifyContent() {
        // Given list of options
        val flavors = listOf("Vanilla", "Chocolate", "Hazelnut", "Cookie", "Mango")
        // And subtotal
        val subtotal = "$100"

        // When SelectOptionScreen is loaded
        composeTestRule.setContent {
            SelectOptionScreen(subtotal = subtotal, options = flavors)
        }

        // Then all the options are displayed on the screen.
        flavors.forEach { flavor ->
            composeTestRule.onNodeWithText(flavor).assertIsDisplayed()
        }

        // And then the subtotal is displayed correctly.
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(
                R.string.subtotal_price,
                subtotal
            )
        ).assertIsDisplayed()

        // And then the next button is disabled
        composeTestRule.onNodeWithStringId(R.string.next).assertIsNotEnabled()
    }

    @Test
    fun startScreen_verifyContent() {
        // When StartOrderScreen is loaded
        composeTestRule.setContent {
            StartOrderScreen(
                quantityOptions = com.cupcake.data.CupcakeDataSource.quantityOptions,
                onNextButtonClicked = {}
            )
        }

        // Then all the options are displayed on the screen
        com.cupcake.data.CupcakeDataSource.quantityOptions.forEach {
            composeTestRule.onNodeWithStringId(it.first).assertIsDisplayed()
        }
    }

    @Test
    fun summaryScreen_verifyContent() {
        // Given an orderUiState
        val mockedOrderUiState = CupcakeOrderUiState(
            quantity = 6,
            flavor = "Vanilla",
            date = "Wed Jul 21",
            price = "$100",
            pickupOptions = listOf()
        )

        // When SummaryScreen is loaded
        composeTestRule.setContent {
            OrderSummaryScreen(
                orderUiState = mockedOrderUiState,
                onCancelButtonClicked = {},
                onSendButtonClicked = { _, _ -> }
            )
        }

        // Then the UI is updated correctly
        composeTestRule.onNodeWithText(mockedOrderUiState.flavor).assertIsDisplayed()
        composeTestRule.onNodeWithText(mockedOrderUiState.date).assertIsDisplayed()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(
                R.string.subtotal_price,
                mockedOrderUiState.price
            )
        ).assertIsDisplayed()
    }

    @Test
    fun flavorScreen_optionSelected_NextButtonEnabled() {
        // Given list of options
        val flavors = listOf("Vanilla", "Chocolate", "Hazelnut", "Cookie", "Mango")
        // And subtotal
        val subtotal = "$100"

        // When SelectOptionScreen is loaded
        composeTestRule.setContent {
            SelectOptionScreen(subtotal = subtotal, options = flavors)
        }

        // And when a flavor is selected
        composeTestRule.onNodeWithText("Mango").performClick()

        // Then next button is enabled
        composeTestRule.onNodeWithStringId(R.string.next).assertIsEnabled()
    }
}