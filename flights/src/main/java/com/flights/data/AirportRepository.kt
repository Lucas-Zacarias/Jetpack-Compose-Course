package com.flights.data

import kotlinx.coroutines.flow.Flow

interface AirportRepository {
    fun getAirportsByName(name: String): Flow<List<Airport>>
    fun getAirportsByIATACode(iataCode: String): Flow<List<Airport>>
}