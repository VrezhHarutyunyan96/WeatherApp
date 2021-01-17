package com.android.weatherapp.renderforest.domain.repository

import com.android.weatherapp.renderforest.data.local.dao.WeatherDao
import com.android.weatherapp.renderforest.data.local.entitiy.WeatherEntity
import com.android.weatherapp.renderforest.domain.model.DailyItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class DataBaseRepository(
    private val weatherDao: WeatherDao
) {
    val getSavedNewsData: Flow<WeatherEntity>
        get() = weatherDao.getLocalWeatherData().flowOn(Dispatchers.IO)

    suspend fun add(weatherEntity: WeatherEntity) {
        weatherDao.insertAll(weatherEntity = weatherEntity)
    }

}