package com.flights.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.flights.FlightApplication
import com.flights.data.AirportRepository
import com.flights.data.FavoriteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FlightsViewModel(
    private val airportRepository: AirportRepository,
    private val favoriteRepository: FavoriteRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(FlightUiState())
    val uiState: StateFlow<FlightUiState> = _uiState.asStateFlow()

    fun changeSearchStrategy(isSearchingByIATACode: Boolean) {
        _uiState.update {
            it.copy(
                currentSearch = "",
                isSearchingByIATACode = isSearchingByIATACode
            )
        }
    }

    fun updateSearch(flightUiState: FlightUiState) {
        _uiState.update { flightUiState }
    }

    companion object {
        val factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as FlightApplication)
                FlightsViewModel(
                    application.container.airportRepository,
                    application.container.favoriteRepository
                )
            }
        }
    }

}

data class FlightUiState(
    var currentSearch: String = "",
    val isSearchingByIATACode: Boolean = false
)