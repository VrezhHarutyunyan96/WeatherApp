package com.android.weatherapp.renderforest.domain.model

data class Params(
    var apiKey: String,
    var lat: String,
    var lon: String,
    var exclude: String,
    var units: String
)
