package com.android.weatherapp.renderforest.domain.model

import com.google.gson.annotations.SerializedName

data class MainWeatherResponse(

//    @SerializedName("lat") val lat: Double,
//    @SerializedName("lon") val lon: Double,
//    @SerializedName("timezone") val timezone: String,
//    @SerializedName("timezone_offset") val timezone_offset: Int,
    @SerializedName("daily") val daily: List<DailyItem>
)

data class Weather(

    @SerializedName("id") val id: Int,
    @SerializedName("main") val main: String,
    @SerializedName("description") val description: String,
    @SerializedName("icon") val icon: String
)

data class Temp(

    @SerializedName("day") val day: Double,
//    @SerializedName("min") val min: Double,
//    @SerializedName("max") val max: Double,
//    @SerializedName("night") val night: Double,
//    @SerializedName("eve") val eve: Double,
//    @SerializedName("morn") val morn: Double
)

//data class Feels_like(
//
//    @SerializedName("day") val day: Double,
//    @SerializedName("night") val night: Double,
//    @SerializedName("eve") val eve: Double,
//    @SerializedName("morn") val morn: Double
//)

data class DailyItem(

    @SerializedName("dt") val dt: Int,
    @SerializedName("temp") val temp: Temp,
//    @SerializedName("pressure") val pressure: Int,
//    @SerializedName("humidity") val humidity: Int,
//    @SerializedName("dew_point") val dew_point: Double,
//    @SerializedName("wind_speed") val wind_speed: Double,
//    @SerializedName("wind_deg") val wind_deg: Int,
    @SerializedName("weather") val weather: List<Weather>,
//    @SerializedName("clouds") val clouds: Int,
//    @SerializedName("pop") val pop: Int,
//    @SerializedName("uvi") val uvi: Double
)
// (
//
//    @field:SerializedName("timezone")
//    val timezone: String? = null,
//
//    @field:SerializedName("timezone_offset")
//    val timezoneOffset: Int? = null,
//
//    @field:SerializedName("daily")
//    val daily: List<DailyItem?>? = null,
//
//    @field:SerializedName("lon")
//    val lon: Double? = null,
//
//    @field:SerializedName("lat")
//    val lat: Double? = null
//)
//
//data class FeelsLike(
//
//    @field:SerializedName("eve")
//    val eve: Double? = null,
//
//    @field:SerializedName("night")
//    val night: Double? = null,
//
//    @field:SerializedName("day")
//    val day: Double? = null,
//
//    @field:SerializedName("morn")
//    val morn: Double? = null
//)
//
//data class Temp(
//
//    @field:SerializedName("min")
//    val min: Double? = null,
//
//    @field:SerializedName("max")
//    val max: Double? = null,
//
//    @field:SerializedName("eve")
//    val eve: Double? = null,
//
//    @field:SerializedName("night")
//    val night: Double? = null,
//
//    @field:SerializedName("day")
//    val day: Double? = null,
//
//    @field:SerializedName("morn")
//    val morn: Double? = null
//)
//
//data class WeatherItem(
//
//    @field:SerializedName("icon")
//    val icon: String? = null,
//
//    @field:SerializedName("description")
//    val description: String? = null,
//
//    @field:SerializedName("main")
//    val main: String? = null,
//
//    @field:SerializedName("id")
//    val id: Int? = null
//)
//
//data class DailyItem(
//
//    @field:SerializedName("temp")
//    val temp: Temp? = null,
//
//    @field:SerializedName("clouds")
//    val clouds: Int? = null,
//
//    @field:SerializedName("feels_like")
//    val feelsLike: FeelsLike? = null,
//
//    @field:SerializedName("dt")
//    val dt: Int? = null,
//
//    @field:SerializedName("pop")
//    val pop: Int? = null,
//
//    @field:SerializedName("wind_deg")
//    val windDeg: Int? = null,
//
//    @field:SerializedName("dew_point")
//    val dewPoint: Double? = null,
//
//    @field:SerializedName("sunset")
//    val sunset: Int? = null,
//
//    @field:SerializedName("weather")
//    val weather: List<WeatherItem?>? = null,
//
//    @field:SerializedName("humidity")
//    val humidity: Int? = null,
//
//    @field:SerializedName("wind_speed")
//    val windSpeed: Double? = null,
//
//    @field:SerializedName("snow")
//    val snow: Double? = null
//)
