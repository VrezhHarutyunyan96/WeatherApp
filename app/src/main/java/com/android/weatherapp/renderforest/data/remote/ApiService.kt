package com.android.weatherapp.renderforest.data.remote

import com.android.weatherapp.renderforest.domain.model.MainWeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Handler Api request
 */

interface ApiService {

    @GET("data/2.5/onecall")
    suspend fun getWeather(
        @Query("appid") apiKey: String,
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("exclude") exclude: String,
        @Query("units") units: String
    ): MainWeatherResponse
}