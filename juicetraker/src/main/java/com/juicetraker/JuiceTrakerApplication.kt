package com.juicetraker

import android.app.Application
import com.juicetraker.data.AppContainer
import com.juicetraker.data.AppDataContainer

class JuiceTrakerApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }

}