package com.flights.data

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AirportDao {
    @Query("""
        SELECT *
        FROM airport
        WHERE name LIKE '%' || :name || '%'
        ORDER BY name ASC
    """)
    fun getAirportsByName(name: String): Flow<List<Airport>>

    @Query("""
        SELECT *
        FROM airport
        WHERE iata_code LIKE '%' || :iataCode || '%'
        ORDER BY iata_code ASC
    """)
    fun getAirportsByIATACode(iataCode: String): Flow<List<Airport>>

    @Query("""
        SELECT *
        FROM airport
        ORDER BY name ASC
    """)
    fun getAllAirportsByName(): Flow<List<Airport>>

    @Query("""
        SELECT *
        FROM airport
        ORDER BY iata_code ASC
    """)
    fun getAllAirportsByIATACode(): Flow<List<Airport>>
}