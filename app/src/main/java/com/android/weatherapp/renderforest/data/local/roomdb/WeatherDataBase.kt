package com.android.weatherapp.renderforest.data.local.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.android.weatherapp.renderforest.data.local.dao.WeatherDao
import com.android.weatherapp.renderforest.data.local.entitiy.WeatherEntity
import com.android.weatherapp.renderforest.utils.Converter

@Database(entities = [WeatherEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class WeatherDataBase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao
}