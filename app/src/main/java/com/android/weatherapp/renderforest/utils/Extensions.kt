@file:Suppress("DEPRECATION")

package com.android.weatherapp.renderforest.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.android.weatherapp.renderforest.domain.model.MainWeatherResponse

fun Context.isNetworkAvailable(): Boolean {
    val connectivityManager =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    //Internet connectivity check in Android Q
    val networks = connectivityManager.allNetworks
    var hasInternet = false
    if (networks.isNotEmpty()) {
        for (network in networks) {
            val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
            if (networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true) hasInternet =
                true
        }
    }
    return hasInternet
}

//fun MainWeatherResponse.toList(): List<MainWeatherResponse.DailyWeather.Weather> {
//    return daily.map { daily ->
//        MainWeatherResponse.DailyWeather.Weather(
//            date = daily.date,
//            timezone = timezone,
//            nightTemp = daily.temp.night,
//            morningTemp = daily.temp.morn,
//            dayTemp = daily.temp.day,
//            eveningTemp = daily.temp.eve,
//            humidity = daily.humidity,
//            windSpeed = daily.windSpeed,
//            icon = daily.weather.firstOrNull()?.icon
//        )
//    }
//}
