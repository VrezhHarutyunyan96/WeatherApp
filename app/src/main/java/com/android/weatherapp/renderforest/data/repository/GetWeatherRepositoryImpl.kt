package com.android.weatherapp.renderforest.data.repository

import com.android.weatherapp.renderforest.data.remote.ApiService
import com.android.weatherapp.renderforest.domain.model.MainWeatherResponse
import com.android.weatherapp.renderforest.domain.model.Params
import com.android.weatherapp.renderforest.domain.repository.GetWeatherRepository

class GetWeatherRepositoryImpl(private val apiService: ApiService) : GetWeatherRepository {

    override suspend fun getWeather(params: Params): MainWeatherResponse {
        return apiService.getWeather(
            apiKey = params.apiKey,
            lat = params.lat,
            lon = params.lon,
            exclude = params.exclude,
            units = params.units
        )
    }
}