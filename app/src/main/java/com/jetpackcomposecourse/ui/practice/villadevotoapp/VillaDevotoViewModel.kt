package com.jetpackcomposecourse.ui.practice.villadevotoapp

import androidx.lifecycle.ViewModel
import com.jetpackcomposecourse.data.Categories
import com.jetpackcomposecourse.data.LocalPlacesDataProvider
import com.jetpackcomposecourse.model.Place
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class VillaDevotoViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(
        VillaDevotoAppUiState(
            placesList = LocalPlacesDataProvider.getPlacesData(),
            currentPlace = LocalPlacesDataProvider.getPlacesData().getOrElse(0) {
                LocalPlacesDataProvider.defaultPlace
            }
        )
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
                currentCategoryIconResourceId = categorySelected.icon
            )
        }
    }
}