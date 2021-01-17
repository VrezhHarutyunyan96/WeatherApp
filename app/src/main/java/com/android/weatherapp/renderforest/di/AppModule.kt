package com.android.weatherapp.renderforest.di

import com.android.weatherapp.renderforest.ui.home.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Koin Module
 * */

val AppModule = module {

    viewModel { HomeViewModel(get(), get()) }

    single { createGetWeatherRepository(get()) }

    single { createGetWeatherUseCase(get()) }
}