package com.android.weatherapp.renderforest.domain.repository

import com.android.weatherapp.renderforest.domain.model.MainWeatherResponse
import com.android.weatherapp.renderforest.domain.model.Params

interface GetWeatherRepository {
    suspend fun getWeather(
        params: Params
    ): MainWeatherResponse
}