package com.android.weatherapp.renderforest.data.local.entitiy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.android.weatherapp.renderforest.utils.Converter


@Entity(tableName = "weather")
data class WeatherEntity(

    @ColumnInfo(name = "daily_entity")
    @TypeConverters(Converter::class)
    var daily: List<DailyEntity>
) {
    @field:ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    data class DailyEntity(
        @ColumnInfo(name = "day") val dt: Int,
        @ColumnInfo(name = "temp") val temp: Temp,
        @TypeConverters(Converter::class)
        @ColumnInfo(name = "day") val weather: List<DayWeather>,
    )

    data class DayWeather(
        @ColumnInfo(name = "main") val main: String?,
        @ColumnInfo(name = "description") val description: String?,
    )

    data class Temp(
        @ColumnInfo(name = "min") val min: Double,
        @ColumnInfo(name = "max") val max: Double,
        @ColumnInfo(name = "eve") val eve: Double,
        @ColumnInfo(name = "night") val night: Double,
        @ColumnInfo(name = "day") val day: Double,
        @ColumnInfo(name = "morn") val morn: Double
    )
}
