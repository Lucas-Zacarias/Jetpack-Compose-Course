package com.flights.data

import kotlinx.coroutines.flow.Flow

class FavoriteRepositoryImpl(
    private val favoriteDao: FavoriteDao
) : FavoriteRepository {
    override fun getAllFavorites(): Flow<List<Favorite>> {
        return favoriteDao.getAllFavorites()
    }

    override suspend fun addFlight(favorite: Favorite) {
        favoriteDao.addFlight(favorite)
    }

    override suspend fun delete(favorite: Favorite) {
        favoriteDao.delete(favorite)
    }

    override suspend fun isFavorite(departureCode: String, destinationCode: String): Int {
        return favoriteDao.isFavorite(departureCode, departureCode)
    }
}