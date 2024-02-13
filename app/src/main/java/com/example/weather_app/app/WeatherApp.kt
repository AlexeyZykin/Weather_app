package com.example.weather_app.app

import android.app.Application
import com.example.weather_app.app.koin.koinModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class WeatherApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@WeatherApp)
            modules(koinModules)
        }
    }
}