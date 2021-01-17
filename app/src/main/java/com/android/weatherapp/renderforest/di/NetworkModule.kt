package com.android.weatherapp.renderforest.di

import com.android.weatherapp.renderforest.clean.BuildConfig
import com.android.weatherapp.renderforest.data.remote.ApiService
import com.android.weatherapp.renderforest.data.repository.GetWeatherRepositoryImpl
import com.android.weatherapp.renderforest.domain.repository.GetWeatherRepository
import com.android.weatherapp.renderforest.domain.usecase.GetWeatherUseCase
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.lang.reflect.Type
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Koin Module
 * */

private const val TIME_OUT = 30L

val NetworkModule = module {
    single { createRetrofit(get(), BuildConfig.BASE_URL) }
    single { createOkHttpClient() }
    single { createService(get()) }

}

fun createOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(TIME_OUT, TimeUnit.SECONDS)
        .build()
}


fun createRetrofit(okHttpClient: OkHttpClient, url: String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder()
                    .registerTypeAdapter(Date::class.java, object : JsonDeserializer<Date> {
                        override fun deserialize(
                            json: JsonElement,
                            typeOfT: Type,
                            context: JsonDeserializationContext
                        ): Date {
                            return Date(json.asJsonPrimitive.asLong * 1000)
                        }
                    }).create()
            )
        )
        .addConverterFactory(MoshiConverterFactory.create()).build()
}

fun createService(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
}


fun createGetWeatherRepository(apiService: ApiService): GetWeatherRepository {
    return GetWeatherRepositoryImpl(apiService)
}

fun createGetWeatherUseCase(repository: GetWeatherRepository): GetWeatherUseCase {
    return GetWeatherUseCase(repository)
}