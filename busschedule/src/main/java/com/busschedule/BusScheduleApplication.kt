package com.busschedule

import android.app.Application
import com.busschedule.data.AppContainer
import com.busschedule.data.AppDataContainer

class BusScheduleApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}