package com.example.task3.ui

import android.app.Application
import com.example.task3.di.apiModule
import com.example.task3.di.catModule
import com.example.task3.di.repositoryModule
import com.example.task3.di.viewModelModule
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(applicationContext)
            modules(listOf(repositoryModule, catModule, viewModelModule, apiModule))
        }
    }
}