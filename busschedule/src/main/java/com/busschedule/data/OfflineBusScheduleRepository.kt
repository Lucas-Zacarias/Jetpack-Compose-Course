package com.busschedule.data

import kotlinx.coroutines.flow.Flow

class OfflineBusScheduleRepository(
    private val scheduleDao: ScheduleDao
) : BusScheduleRepository {
    override fun getAllSchedules(): Flow<List<BusSchedule>> {
        return scheduleDao.getAllSchedules()
    }

    override fun getSchedule(name: String): Flow<List<BusSchedule>> {
        return scheduleDao.getSchedule(name)
    }
}