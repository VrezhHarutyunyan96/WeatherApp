package com.android.weatherapp.renderforest.data.local.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.weatherapp.renderforest.data.local.dao.WeatherDao
import com.android.weatherapp.renderforest.data.local.entitiy.WeatherEntity

@Database(
    entities = [WeatherEntity::class], version = 1, exportSchema = false
)
abstract class WeatherDataBase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao
}