package com.example.api

import com.example.data.remote.WeatherResponse
import com.example.util.Constants.Companion.API_ID
import retrofit2.Response
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
    ) : Response<WeatherResponse>

}