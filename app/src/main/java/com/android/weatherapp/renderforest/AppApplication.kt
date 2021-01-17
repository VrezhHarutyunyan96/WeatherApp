package com.android.weatherapp.renderforest

import android.app.Application
import com.android.weatherapp.renderforest.di.AppModule
import com.android.weatherapp.renderforest.di.DBModule
import com.android.weatherapp.renderforest.di.NetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/** Application class.
 *  calling first in the app and has application context
 **/


class AppApplication : Application() {

    init {
        instance = this
    }

    companion object {
        var instance: AppApplication? = null
            private set
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@AppApplication)
            modules(listOf(AppModule, NetworkModule, DBModule(this@AppApplication)))
        }
    }
}