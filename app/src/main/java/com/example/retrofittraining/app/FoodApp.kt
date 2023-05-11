package com.example.retrofittraining.app

import android.app.Application
import com.example.retrofittraining.di.dataModule
import com.example.retrofittraining.di.domainModule
import com.example.retrofittraining.di.networkModule
import com.example.retrofittraining.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class FoodApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@FoodApp)
            modules(listOf(viewModelModule, dataModule, domainModule, networkModule))


        }
    }
}