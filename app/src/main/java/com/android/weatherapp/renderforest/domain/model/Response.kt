package com.android.weatherapp.renderforest.domain.model

import com.google.gson.annotations.SerializedName

/**
 * Main response model
 */

data class MainWeatherResponse(

    @SerializedName("daily") val daily: List<DailyItem>
)

data class Weather(

    @SerializedName("main") val main: String,
    @SerializedName("description") val description: String,
)

data class Temp(

    @SerializedName("day") val day: Double,
    @SerializedName("min") val min: Double,
    @SerializedName("max") val max: Double,
    @SerializedName("night") val night: Double,
    @SerializedName("eve") val eve: Double,
    @SerializedName("morn") val morn: Double
)

data class DailyItem(

    @SerializedName("dt") val dt: Int,
    @SerializedName("temp") val temp: Temp,
    @SerializedName("weather") val weather: List<Weather>,
)
