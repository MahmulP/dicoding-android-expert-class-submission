package com.mahmulp.storyapp

import android.app.Application
import com.mahmulp.core.di.databaseModule
import com.mahmulp.core.di.networkModule
import com.mahmulp.core.di.repositoryModule
import com.mahmulp.storyapp.di.storageModule
import com.mahmulp.storyapp.di.useCaseModule
import com.mahmulp.storyapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level.NONE

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule,
                    storageModule,

                )
            )
        }
    }
}