package com.android.weatherapp.renderforest.utils

import androidx.room.TypeConverter
import com.android.weatherapp.renderforest.data.local.entitiy.WeatherEntity
import com.android.weatherapp.renderforest.domain.model.MainWeatherResponse
import com.google.gson.Gson

class Converter {
    @TypeConverter
    fun dailyItemListToJson(value: List<WeatherEntity.DailyEntity?>?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun dailyItemJsonToList(value: String): List<WeatherEntity.DailyEntity?>? {
        if (Gson().fromJson(value, Array<WeatherEntity.DailyEntity?>::class.java) != null) {
            val objects = Gson().fromJson(
                value,
                Array<WeatherEntity.DailyEntity?>::class.java
            ) as Array<WeatherEntity.DailyEntity?>
            return objects.toList()
        }
        return null
    }

    @TypeConverter
    fun weatherItemListToJson(value: List<WeatherEntity.DayWeather?>?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun weatherItemJsonToList(value: String): List<WeatherEntity.DayWeather?>? {
        if (Gson().fromJson(value, Array<WeatherEntity.DayWeather?>::class.java) != null) {
            val objects = Gson().fromJson(
                value,
                Array<WeatherEntity.DayWeather?>::class.java
            ) as Array<WeatherEntity.DayWeather?>
            return objects.toList()
        }
        return null
    }
}

/**
 * Mapping response data
 */

class PojoToEntity() : Mapper<MainWeatherResponse, WeatherEntity> {

    override fun map(model: MainWeatherResponse): WeatherEntity {
        model.daily.let {

            val dailyItemsList = mutableListOf<WeatherEntity.DailyEntity>()

            for (item in model.daily) {

                item.let { dailyItem ->

                    val tempEntityModel: WeatherEntity.Temp = WeatherEntity.Temp(
                        dailyItem.temp.min,
                        dailyItem.temp.max,
                        dailyItem.temp.eve,
                        dailyItem.temp.night,
                        dailyItem.temp.day,
                        dailyItem.temp.morn
                    )
                    val DayWeatherEntityModelList = mutableListOf<WeatherEntity.DayWeather>()
                    dailyItem.weather.let {
                        for (weatherItem in dailyItem.weather) {
                            val weatherEntityModel = WeatherEntity.DayWeather(
                                weatherItem.description,
                                weatherItem.main,
                            )
                            DayWeatherEntityModelList.add(weatherEntityModel)
                        }
                    }

                    val entityModel = WeatherEntity.DailyEntity(
                        dailyItem.dt,
                        tempEntityModel,
                        DayWeatherEntityModelList,
                    )
                    dailyItemsList.add(entityModel)
                }
            }

            return WeatherEntity(
                dailyItemsList,
            )
        }
    }
}

interface Mapper<R, L> {

    fun map(model: R): L
}