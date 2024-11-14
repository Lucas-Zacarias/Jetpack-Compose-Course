package com.waterme.data

import com.waterme.model.Plant
import java.util.concurrent.TimeUnit

interface WaterRepository {
    fun scheduleReminder(duration: Long, unit: TimeUnit, plantName: String)
    val plants: List<Plant>
}