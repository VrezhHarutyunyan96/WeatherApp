package com.android.weatherapp.renderforest.domain.repository

import com.android.weatherapp.renderforest.data.local.dao.WeatherDao
import com.android.weatherapp.renderforest.data.local.entitiy.WeatherEntity
import com.android.weatherapp.renderforest.domain.model.DailyItem
import kotlinx.coroutines.flow.Flow

class DataBaseRepository(
    private val weatherDao: WeatherDao
) {
    val getSavedNewsData: Flow<WeatherEntity>
        get() = weatherDao.getLocalWeatherData()

    fun add(weatherEntity: WeatherEntity) {
        weatherDao.insertAll(weatherEntity = weatherEntity)
    }

}