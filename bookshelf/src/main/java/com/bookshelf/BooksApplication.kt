package com.bookshelf

import android.app.Application
import com.bookshelf.data.AppContainer
import com.bookshelf.data.DefaultAppContainer

class BooksApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}