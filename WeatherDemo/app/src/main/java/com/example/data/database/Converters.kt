package com.example.data.database

import android.accounts.AuthenticatorDescription
import androidx.room.TypeConverters
import com.example.data.models.Temp
import com.example.data.models.Weather

class Converters {

    @TypeConverters
    fun fromTemp(temp: Temp): List<Double> {
        return listOf(temp.min, temp.max)
    }

    @TypeConverters
    fun toTemp(min: Double, max: Double): Temp {
        return Temp(min,max)
    }

    @TypeConverters
    fun fromWeather(weather: Weather): HashMap<String, String> {
        return hashMapOf(Pair(weather.main, weather.description))
    }

    @TypeConverters
    fun toWeather(main: String, description: String) : Weather {
        return Weather(main = main, description = description)
    }
}