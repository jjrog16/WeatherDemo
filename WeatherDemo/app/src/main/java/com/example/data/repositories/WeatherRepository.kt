package com.example.data.repositories

import com.example.api.RetrofitInstance
import com.example.data.database.OpenWeatherDatabase

class WeatherRepository(
        val db: OpenWeatherDatabase
) {
    suspend fun getOpenWeather(appId: String, exclude: String, latitude: Double, longitude: Double, units: String) =
            RetrofitInstance.api.getDailyWeather(
                    appId = appId,
                    exclude = exclude,
                    latitude = latitude,
                    longitude = longitude,
                    units = units
            )
}