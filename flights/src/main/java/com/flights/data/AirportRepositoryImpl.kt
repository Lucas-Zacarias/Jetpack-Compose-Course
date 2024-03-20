package com.flights.data

import kotlinx.coroutines.flow.Flow

class AirportRepositoryImpl(
    private val airportDao: AirportDao
) : AirportRepository {
    override fun getAirportsByName(name: String): Flow<List<Airport>> {
        return airportDao.getAirportsByName(name)
    }

    override fun getAirportsByIATACode(iataCode: String): Flow<List<Airport>> {
        return airportDao.getAirportsByIATACode(iataCode)
    }
}