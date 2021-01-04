package com.example.ui.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.data.database.OpenWeatherDatabase
import com.example.data.repositories.WeatherRepository

class WeatherActivity : AppCompatActivity() {

    lateinit var viewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        val weatherRepository = WeatherRepository(OpenWeatherDatabase(this))
        val viewModelFactory = WeatherViewModelFactory(weatherRepository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(WeatherViewModel::class.java)
    }
}