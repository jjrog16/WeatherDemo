package com.example.api

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    @GET("data/2.5/onecall")
    suspend fun getDailyWeather(
            @Query("lat")
            latitude: Double,
            @Query("lon")
            longitude: Double,
            @Query("units")
            units: String,
            @Query("appid")
            appId: String = API_ID,
            @Query("exclude")
            exclude: String
    )

}