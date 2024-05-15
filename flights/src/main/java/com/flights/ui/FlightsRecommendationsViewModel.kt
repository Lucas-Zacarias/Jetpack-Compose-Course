package com.flights.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.flights.FlightApplication
import com.flights.data.Airport
import com.flights.data.AirportRepository
import com.flights.data.FavoriteRepository
import com.flights.data.RecommendationFlight
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FlightsRecommendationsViewModel(
    private val airportRepository: AirportRepository,
    private val favoriteRepository: FavoriteRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        getAllAirports()
    }

    fun getFlightsRecommendations(airportCode: String, airportName: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    currentAirportCode = airportCode,
                    currentAirportName = airportName
                )
            }
            val airports = _uiState.value.airports.filter { it.iataCode != airportCode }
            val recommendedFlights = mutableListOf<RecommendationFlight>()
            airports.forEach { airport ->
                recommendedFlights.add(
                        RecommendationFlight(
                            departureIataCode = airportCode,
                            departureAirportName = airportName,
                            destinationIataCode = airport.iataCode,
                            destinationAirportName = airport.name,
                            isFavorite = favoriteRepository.isFavorite(airportCode, airport.iataCode) == 1
                        )
                    )

            }
            _uiState.update {
                it.copy(
                    recommendedFlights = recommendedFlights
                )
            }
        }
    }

    fun addFlightToFavorites(flight: RecommendationFlight) {
        viewModelScope.launch {
            favoriteRepository.addFlight(flight.recommendationFlightToFavorite())
            _uiState.update { uiState ->
                uiState.copy(
                    recommendedFlights = uiState.recommendedFlights.map {
                        if (it.departureIataCode == flight.departureIataCode &&
                            it.destinationIataCode == flight.destinationIataCode) {
                            it.copy(isFavorite = true)
                        } else {
                            it.copy(isFavorite = false)
                        }
                    }
                )
            }
        }
    }

    fun removeFlightFromFavorites(flight: RecommendationFlight) {
        viewModelScope.launch {
            favoriteRepository.delete(flight.recommendationFlightToFavorite())
            _uiState.update { uiState ->
                uiState.copy(
                    recommendedFlights = uiState.recommendedFlights.map {
                        if (it.departureIataCode == flight.departureIataCode && it.destinationIataCode == flight.destinationIataCode) {
                            it.copy(isFavorite = false)
                        } else {
                            it.copy(isFavorite = true)
                        }
                    }
                )
            }
        }
    }

    private fun getAllAirports() {
        viewModelScope.launch {
            airportRepository.getAllAirportsByName().collect { airports ->
                _uiState.update {
                    it.copy(
                        airports = airports
                    )
                }
            }
        }
    }


    companion object {
        val factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as FlightApplication)
                FlightsRecommendationsViewModel(
                    application.container.airportRepository,
                    application.container.favoriteRepository
                )
            }
        }
    }

}

data class UiState(
    val currentAirportCode: String = "",
    val currentAirportName: String = "",
    val airports: List<Airport> = emptyList(),
    val recommendedFlights: List<RecommendationFlight> = emptyList(),
)