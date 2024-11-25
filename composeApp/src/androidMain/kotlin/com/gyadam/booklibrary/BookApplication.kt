package com.gyadam.booklibrary

import android.app.Application
import com.gyadam.booklibrary.di.initKoin

class BookApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}