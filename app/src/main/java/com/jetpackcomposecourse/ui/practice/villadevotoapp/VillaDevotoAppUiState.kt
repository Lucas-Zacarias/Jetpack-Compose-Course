package com.jetpackcomposecourse.ui.practice.villadevotoapp

import androidx.annotation.DrawableRes
import com.jetpackcomposecourse.data.Categories
import com.jetpackcomposecourse.data.LocalPlacesDataProvider
import com.jetpackcomposecourse.model.Place

data class VillaDevotoAppUiState(
    val placesList: List<Place> = emptyList(),
    val currentPlace: Place = LocalPlacesDataProvider.defaultPlace,
    val currentCategory: Categories = Categories.COFFEE_SHOPS,
    @DrawableRes val currentCategoryIconResourceId: Int = currentCategory.icon
)
