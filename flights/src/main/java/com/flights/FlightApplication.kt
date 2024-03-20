package com.flights

import android.app.Application
import com.flights.data.AppContainer
import com.flights.data.AppDataContainer

class FlightApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}