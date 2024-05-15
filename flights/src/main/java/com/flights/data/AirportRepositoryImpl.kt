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

    override fun getAllAirportsByName(): Flow<List<Airport>> {
        return airportDao.getAllAirportsByName()
    }

    override fun getAllAirportsByIATACode(): Flow<List<Airport>> {
        return airportDao.getAllAirportsByIATACode()
    }

    override fun getAirportByIATACode(iataCode: String): Flow<Airport> {
        return airportDao.getAirportByCod(iataCode)
    }
}