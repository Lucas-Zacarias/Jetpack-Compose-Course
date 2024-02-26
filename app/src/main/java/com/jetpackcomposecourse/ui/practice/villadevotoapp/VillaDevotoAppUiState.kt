package com.jetpackcomposecourse.ui.practice.villadevotoapp

import com.jetpackcomposecourse.data.Categories
import com.jetpackcomposecourse.data.LocalPlacesDataProvider
import com.jetpackcomposecourse.model.Place

data class VillaDevotoAppUiState(
    val currentCategory: Categories = Categories.COFFEE_SHOPS,
    val placesList: List<Place> = LocalPlacesDataProvider.getPlacesByCategory(currentCategory),
    val currentPlace: Place = LocalPlacesDataProvider.defaultPlace
)
