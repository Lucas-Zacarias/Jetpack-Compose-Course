package com.flights.data

import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    fun getAllFavorites(): Flow<List<Favorite>>
    suspend fun addFlight(favorite: Favorite)
    suspend fun delete(favorite: Favorite)
    suspend fun isFavorite(departureCode: String, destinationCode: String): Int
}