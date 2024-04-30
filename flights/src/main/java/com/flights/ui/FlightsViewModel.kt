package com.flights.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.flights.FlightApplication
import com.flights.data.Airport
import com.flights.data.AirportRepository
import com.flights.data.FavoriteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FlightsViewModel(
    private val airportRepository: AirportRepository,
    private val favoriteRepository: FavoriteRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(FlightUiState())
    val uiState = _uiState.asStateFlow()

    init {
        searchAirports()
    }

    fun changeSearchStrategy(isSearchingByIATACode: Boolean) {
        _uiState.update {
            it.copy(
                currentSearch = "",
                isSearchingByIATACode = isSearchingByIATACode
            )
        }
        searchAirports()
    }

    fun searchAirports(currentSearch: String? = null) {
        viewModelScope.launch {
            if (currentSearch != null) {
                if (_uiState.value.isSearchingByIATACode) {
                    airportRepository.getAirportsByIATACode(currentSearch.lowercase())
                        .collect { airports ->
                            _uiState.update {
                                it.copy(
                                    airports = airports
                                )
                            }
                        }
                } else {
                    airportRepository.getAirportsByName(currentSearch.lowercase())
                        .collect { airports ->
                            _uiState.update {
                                it.copy(
                                    airports = airports
                                )
                            }
                        }
                }
            } else if (_uiState.value.isSearchingByIATACode) {
                airportRepository.getAllAirportsByIATACode()
                    .collect { airports ->
                        _uiState.update {
                            it.copy(
                                airports = airports
                            )
                        }
                    }
            } else {
                airportRepository.getAllAirportsByName()
                    .collect { airports ->
                        _uiState.update {
                            it.copy(
                                airports = airports
                            )
                        }
                    }
            }
        }
    }

    fun updateCurrentSearch(flightUiState: FlightUiState) {
        _uiState.value = flightUiState
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
    val isSearchingByIATACode: Boolean = false,
    val airports: List<Airport> = emptyList()
)