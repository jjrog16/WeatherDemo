package com.example.ui.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.models.Weather
import com.example.data.repositories.WeatherRepository

class WeatherViewModelFactory(
        val weatherRepository: WeatherRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WeatherViewModel(weatherRepository) as T
    }
}