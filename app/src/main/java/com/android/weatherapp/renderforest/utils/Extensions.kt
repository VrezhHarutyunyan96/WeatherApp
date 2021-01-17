package com.android.weatherapp.renderforest.utils

fun String.getTempString(): String {
    return this.substringBefore(".") + "°C"
}