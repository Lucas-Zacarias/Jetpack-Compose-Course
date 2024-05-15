package com.flights.data

data class RecommendationFlight(
    val departureIataCode: String,
    val departureAirportName: String,
    val destinationIataCode: String,
    val destinationAirportName: String,
    val isFavorite: Boolean
) {
    fun recommendationFlightToFavorite(): Favorite {
        return Favorite(
            departureCode = departureIataCode,
            destinationCode = destinationIataCode
        )
    }
}