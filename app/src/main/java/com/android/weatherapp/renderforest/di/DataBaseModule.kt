package com.android.weatherapp.renderforest.di

import android.content.Context
import androidx.room.Room
import com.android.weatherapp.renderforest.data.local.roomdb.WeatherDataBase
import com.android.weatherapp.renderforest.domain.repository.DataBaseRepository
import org.koin.dsl.module

fun DBModule(appContext: Context) = module {
    single {   Room.databaseBuilder(appContext, WeatherDataBase::class.java, "news_database")
        .build() }
    single { get<WeatherDataBase>().weatherDao() }
    single { DataBaseRepository(get()) }
}
