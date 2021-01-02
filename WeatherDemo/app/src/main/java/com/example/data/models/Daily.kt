package com.example.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Daily(
    @PrimaryKey
    val dt: Int,
    val clouds: Int,
    val dew_point: Double,
    val feels_like: FeelsLike,
    val humidity: Int,
    val pop: Int,
    val pressure: Int,
    val rain: Double,
    val sunrise: Int,
    val sunset: Int,
    val temp: Temp,
    val uvi: Double,
    val weather: List<Weather>,
    val wind_deg: Int,
    val wind_speed: Double
)