package com.example.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Daily(
    @PrimaryKey
    val dt: Int,
    val sunrise: Int,
    val sunset: Int,
    val temp: Temp,
    val weather: List<Weather>
)