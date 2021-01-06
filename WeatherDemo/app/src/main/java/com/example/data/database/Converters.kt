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
    fun fromWeather(weather: Weather): String {
        return weather.main
    }

    @TypeConverter
    fun toWeather(main: String) : Weather {
        return Weather(main)
    }
}