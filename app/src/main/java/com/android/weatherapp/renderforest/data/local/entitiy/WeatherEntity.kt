package com.android.weatherapp.renderforest.data.local.entitiy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "weather")
data class WeatherEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "daily_entity")
    var daily: List<DailyEntity?>
) {
    constructor() : this(
        mutableListOf(
            DailyEntity(
                1000,
                5,
                mutableListOf(DayWeather(10, "", "", ""))
            )
        )
    )
}


@Entity(tableName = "daily")
data class DailyEntity(
    @ColumnInfo(name = "day") val dt: Int,
    @ColumnInfo(name = "temp") val temp: Int,
    @ColumnInfo(name = "day") val weather: List<DayWeather>,
)

@Entity(tableName = "day_weather")
data class DayWeather(

    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "main") val main: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "icon") val icon: String
)

