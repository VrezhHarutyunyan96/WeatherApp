package com.android.weatherapp.renderforest.domain.usecase

import com.android.weatherapp.renderforest.base.BaseUseCase
import com.android.weatherapp.renderforest.domain.model.MainWeatherResponse
import com.android.weatherapp.renderforest.domain.model.Params
import com.android.weatherapp.renderforest.domain.repository.GetWeatherRepository

class GetWeatherUseCase constructor(
    private val weatherRepository: GetWeatherRepository
) : BaseUseCase<MainWeatherResponse, Params>() {
    override suspend fun run(params: Params): MainWeatherResponse {
        return weatherRepository.getWeather(params)
    }
}