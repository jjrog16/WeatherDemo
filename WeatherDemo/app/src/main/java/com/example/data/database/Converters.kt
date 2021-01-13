package com.example.data.database

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.data.models.Temp
import com.example.data.models.Weather

class Converters {

    @TypeConverter
    fun fromTemp(temp: Temp): Double {
        return temp.min
    }

    @TypeConverter
    fun toTemp(min: Double): Temp {
        return Temp(min = min)
    }

    @TypeConverter
    fun fromWeather(weather: List<Weather>): String {
        return weather[0].main
    }

    @TypeConverter
    fun toWeather(main: String) : List<Weather> {
        return listOf(Weather(main))
    }
}