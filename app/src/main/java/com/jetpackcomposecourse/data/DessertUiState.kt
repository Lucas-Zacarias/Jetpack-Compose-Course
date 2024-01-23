package com.jetpackcomposecourse.data

import androidx.annotation.DrawableRes
import com.jetpackcomposecourse.R
import com.jetpackcomposecourse.data.Datasource.dessertList

data class DessertUiState(
    val revenue: Int = 0,
    val dessertsSold: Int = 0,
    val currentDessertIndex: Int = 0,
    val currentDessertPrice: Int = dessertList[currentDessertIndex].price,
    @DrawableRes val currentDessertImageId: Int = R.drawable.cupcake
)