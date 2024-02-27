package com.tipcalculator

import com.tipcalculator.ui.calculateTip
import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.NumberFormat

class TipCalculatorTests {

    @Test
    fun `test calculate tip 20 percent no round up`() {
        val amount = 10.00
        val tipPercent = 20.00
        val expectedTip = NumberFormat.getCurrencyInstance().format(2)
        val actualTip = calculateTip(amount = amount, tipPercent = tipPercent, false)
        assertEquals(expectedTip, actualTip)
    }

    @Test
    fun `test calculate tip 20 percent with round up`() {
        val amount = 150.00
        val tipPercent = 17.00
        val expectedTip = NumberFormat.getCurrencyInstance().format(26)
        val actualTip = calculateTip(amount = amount, tipPercent = tipPercent, true)
        assertEquals(expectedTip, actualTip)
    }

}