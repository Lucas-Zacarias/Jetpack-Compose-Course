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

    @Insert
    suspend fun addFlight(favorite: Favorite)

    @Delete
    suspend fun delete(favorite: Favorite)
}