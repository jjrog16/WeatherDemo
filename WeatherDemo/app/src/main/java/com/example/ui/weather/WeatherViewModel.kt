package com.example.ui.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.models.WeatherResponse
import com.example.data.repositories.WeatherRepository
import com.example.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class WeatherViewModel(
        val weatherRepository: WeatherRepository
) : ViewModel() {

    val openWeather: MutableLiveData<Resource<WeatherResponse>> = MutableLiveData()

    init {
        //getBreakingNews("us")
    }

    /*fun getOpenWeather(countryCode: String) = viewModelScope.launch {
        breakingNews.postValue(Resource.Loading())
        val response = newsRepository.getBreakingNews(countryCode, breakingNewsPage)
        breakingNews.postValue(handleBreakingNewsResponse(response))
    }*/

    /*private fun handleBreakingNewsResponse(response: Response<WeatherResponse>) : Resource<WeatherResponse> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }*/
}