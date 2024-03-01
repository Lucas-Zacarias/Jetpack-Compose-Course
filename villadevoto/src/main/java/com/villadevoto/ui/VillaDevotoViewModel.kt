package com.jetpackcomposecourse.ui.practice.villadevotoapp

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

    fun updateCurrentPlace(selectedPlace: com.villadevoto.model.Place) {
        _uiState.update {
            it.copy(currentPlace = selectedPlace)
        }
    }

    fun updateCurrentCategory(categorySelected: com.villadevoto.data.Categories) {
        _uiState.update {
            it.copy(
                currentCategory = categorySelected,
                placesList = com.villadevoto.data.LocalPlacesDataProvider.getPlacesByCategory(categorySelected),
                currentPlace = com.villadevoto.data.LocalPlacesDataProvider.getPlacesByCategory(categorySelected).first()
            )
        }
    }
}