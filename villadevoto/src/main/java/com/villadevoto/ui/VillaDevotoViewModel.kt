package com.villadevoto.ui

import androidx.lifecycle.ViewModel
import com.villadevoto.data.Categories
import com.villadevoto.data.LocalPlacesDataProvider
import com.villadevoto.model.Place
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class VillaDevotoViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(
        VillaDevotoAppUiState()
    )
    val uiState: StateFlow<VillaDevotoAppUiState> = _uiState

    fun updateCurrentPlace(selectedPlace: Place) {
        _uiState.update {
            it.copy(currentPlace = selectedPlace)
        }
    }

    fun updateCurrentCategory(categorySelected: Categories) {
        _uiState.update {
            it.copy(
                currentCategory = categorySelected,
                placesList = LocalPlacesDataProvider.getPlacesByCategory(categorySelected),
                currentPlace = LocalPlacesDataProvider.getPlacesByCategory(categorySelected).first()
            )
        }
    }
}