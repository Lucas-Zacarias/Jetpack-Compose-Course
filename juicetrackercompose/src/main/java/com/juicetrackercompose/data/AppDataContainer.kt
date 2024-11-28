package com.juicetrackercompose.data

import android.content.Context

class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * Implementation for [JuiceRepository]
     */
    override val juiceRepository: JuiceRepository by lazy {
        RoomJuiceRepository(AppDatabase.getDatabase(context).juiceDao())
    }
}