package com.gyadam.booklibrary

import android.app.Application
import com.gyadam.booklibrary.di.initKoin
import org.koin.android.ext.koin.androidContext

class BookApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@BookApplication)
        }
    }
}