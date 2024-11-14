package com.waterme

import android.app.Application
import com.waterme.data.AppContainer
import com.waterme.data.DefaultAppContainer

class WaterMeApplication : Application() {
    /** AppContainer instance used by the rest of classes to obtain dependencies */
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
    }
}