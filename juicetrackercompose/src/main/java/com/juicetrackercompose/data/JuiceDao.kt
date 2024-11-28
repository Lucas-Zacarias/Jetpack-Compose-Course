package com.juicetrackercompose.data

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import androidx.room.Dao

@Dao
interface JuiceDao {
    @Query("SELECT * FROM juice")
    fun getAll(): Flow<List<Juice>>

    @Insert
    suspend fun insert(juice: Juice)

    @Delete
    suspend fun delete(juice: Juice)

    @Update
    suspend fun update(juice: Juice)
}