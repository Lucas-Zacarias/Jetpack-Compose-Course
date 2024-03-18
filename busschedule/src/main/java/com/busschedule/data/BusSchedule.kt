package com.busschedule.data

import androidx.room.Entity

@Entity(tableName = "Schedule")
data class BusSchedule(
    val id: Int,
    val stopName: String,
    val arrivalTimeInMillis: Int
)
