package com.example.ui.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.models.WeatherResponse
import com.example.data.repositories.WeatherRepository
import com.example.util.Constants.Companion.API_ID
import com.example.util.Constants.Companion.LATITUDE
import com.example.util.Constants.Companion.LONGITUDE
import com.example.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class WeatherViewModel(
        val weatherRepository: WeatherRepository
) : ViewModel() {

    val openWeather: MutableLiveData<Resource<WeatherResponse>> = MutableLiveData()
    var units = "imperial"

    init {
        getOpenWeather(
                appid = API_ID,
                exclude = "minutely,hourly,alerts,current,feels_like",
                latitude = LATITUDE,
                longitude = LONGITUDE,
                units = units
        )
    }

    fun getOpenWeather(appid: String, exclude: String, latitude: Double, longitude: Double, units: String) = viewModelScope.launch {
        openWeather.postValue(Resource.Loading())
        val response = weatherRepository.getOpenWeather(appid, exclude, latitude, longitude, units)
        openWeather.postValue(handleOpenWeatherResponse(response))
    }

    private fun handleOpenWeatherResponse(response: Response<WeatherResponse>) : Resource<WeatherResponse> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}