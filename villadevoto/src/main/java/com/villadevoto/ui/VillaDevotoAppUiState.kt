package com.jetpackcomposecourse.ui.practice.villadevotoapp

import com.villadevoto.data.Categories
import com.villadevoto.data.LocalPlacesDataProvider
import com.villadevoto.model.Place

data class VillaDevotoAppUiState(
    val currentCategory: com.villadevoto.data.Categories = com.villadevoto.data.Categories.COFFEE_SHOPS,
    val placesList: List<com.villadevoto.model.Place> = com.villadevoto.data.LocalPlacesDataProvider.getPlacesByCategory(currentCategory),
    val currentPlace: com.villadevoto.model.Place = com.villadevoto.data.LocalPlacesDataProvider.defaultPlace
)
