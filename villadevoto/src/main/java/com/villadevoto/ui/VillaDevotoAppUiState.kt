package com.villadevoto.ui

import com.villadevoto.data.Categories
import com.villadevoto.data.LocalPlacesDataProvider
import com.villadevoto.model.Place

data class VillaDevotoAppUiState(
    val currentCategory: Categories = Categories.COFFEE_SHOPS,
    val placesList: List<Place> = LocalPlacesDataProvider.getPlacesByCategory(currentCategory),
    val currentPlace: Place = LocalPlacesDataProvider.defaultPlace
)
