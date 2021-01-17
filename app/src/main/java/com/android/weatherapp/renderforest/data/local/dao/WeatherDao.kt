package com.android.weatherapp.renderforest.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.weatherapp.renderforest.data.local.entitiy.WeatherEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Query("SELECT * FROM weather")
    fun getLocalWeatherData(): Flow<WeatherEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertAll(weatherEntity: WeatherEntity)

}