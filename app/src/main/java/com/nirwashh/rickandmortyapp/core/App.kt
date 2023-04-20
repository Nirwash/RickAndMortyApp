package com.nirwashh.rickandmortyapp.core

import android.app.Application
import com.nirwashh.rickandmortyapp.core.di.AppComponent
import com.nirwashh.rickandmortyapp.core.di.AppModule
import com.nirwashh.rickandmortyapp.core.di.DaggerAppComponent

class App : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this@App))
            .build()

    }
}