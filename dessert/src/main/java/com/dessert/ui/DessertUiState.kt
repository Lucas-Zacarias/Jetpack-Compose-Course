package com.dessert.ui

import androidx.annotation.DrawableRes
import com.dessert.R
import com.dessert.data.Datasource.dessertList

data class DessertUiState(
    val revenue: Int = 0,
    val dessertsSold: Int = 0,
    val currentDessertIndex: Int = 0,
    val currentDessertPrice: Int = dessertList[currentDessertIndex].price,
    @DrawableRes val currentDessertImageId: Int = R.drawable.cupcake
)