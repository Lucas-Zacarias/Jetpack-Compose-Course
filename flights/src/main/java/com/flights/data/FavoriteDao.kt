package com.flights.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Query("""
        SELECT *
        FROM favorite
    """)
    fun getAllFavorites(): Flow<List<Favorite>>

    @Query(
        """
        SELECT 
        EXISTS(
                SELECT 1 
                FROM favorite
                WHERE departure_code = :departureCode AND destination_code = :destinationCode
                )
    """
    )
    suspend fun isFavorite(departureCode: String, destinationCode: String): Int

    @Insert
    suspend fun addFlight(favorite: Favorite)

    @Delete
    suspend fun delete(favorite: Favorite)
}