package com.busschedule.data

import kotlinx.coroutines.flow.Flow

interface BusScheduleRepository {
    fun getAllSchedules(): Flow<List<BusSchedule>>

    fun getSchedule(name: String): Flow<List<BusSchedule>>
}
