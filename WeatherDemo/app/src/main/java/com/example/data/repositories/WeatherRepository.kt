package com.example.data.repositories

import com.example.api.RetrofitInstance
import com.example.data.database.OpenWeatherDatabase

class WeatherRepository(
        val db: OpenWeatherDatabase
) {
    suspend fun getOpenWeather(exclude: String, latitude: Double, longitude: Double, units: String) =
            RetrofitInstance.api.getDailyWeather(
                    exclude = exclude,
                    latitude = latitude,
                    longitude = longitude,
                    units = units
            )
}