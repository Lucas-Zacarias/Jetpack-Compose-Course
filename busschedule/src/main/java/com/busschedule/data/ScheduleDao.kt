package com.busschedule.data

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ScheduleDao {

    @Query(
        """
        SELECT * 
        FROM schedule 
        ORDER BY arrival_time ASC
    """
    )
    fun getAllSchedules(): Flow<List<BusSchedule>>

    @Query(
        """
        SELECT * 
        FROM schedule 
        WHERE stop_name = :name 
        ORDER BY arrival_time ASC
    """
    )
    fun getSchedule(name: String): Flow<List<BusSchedule>>

}