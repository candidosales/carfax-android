package com.carfax.application

import android.app.Application
import com.carfax.application.di.appModule
import com.carfax.application.di.networkModule
import com.carfax.application.di.utilsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

open class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupTimber()

        startKoin {
            androidContext(this@MainApplication)
            modules(appModule, networkModule, utilsModule)
        }

    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}