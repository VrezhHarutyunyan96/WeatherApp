package com.android.weatherapp.renderforest.utils

import com.android.weatherapp.renderforest.data.local.entitiy.DailyEntity
import com.android.weatherapp.renderforest.data.local.entitiy.DayWeather
import com.android.weatherapp.renderforest.data.local.entitiy.WeatherEntity
import com.android.weatherapp.renderforest.domain.model.DailyItem

fun convertModelToEntity(list: List<DailyItem>): List<DailyEntity?> {
    val weatherEntity = WeatherEntity(mutableListOf(DailyEntity(1000,5, mutableListOf(DayWeather(10,"","","")))))
    return weatherEntity.daily.apply {

    }

//        dt =
//        date = newsItem.pubDate
//        title = newsItem.title
//        link = newsItem.link

}