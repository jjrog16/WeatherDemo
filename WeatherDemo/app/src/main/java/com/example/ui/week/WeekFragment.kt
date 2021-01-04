package com.example.ui.week

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adapters.DailyAdapter
import com.example.ui.weather.R
import com.example.ui.weather.WeatherActivity
import com.example.ui.weather.WeatherViewModel
import com.example.util.Resource
import kotlinx.android.synthetic.main.fragment_week.*
import timber.log.Timber

class WeekFragment : Fragment(R.layout.fragment_week) {

    lateinit var viewModel: WeatherViewModel
    lateinit var dailyAdapter: DailyAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as WeatherActivity).viewModel
        setupRecyclerView()

        viewModel.openWeather.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { weatherResponse ->
                        dailyAdapter.differ.submitList(weatherResponse.daily)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Timber.i("An error occured: $message")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }

    private fun hideProgressBar() {
        paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        paginationProgressBar.visibility = View.VISIBLE
    }

    private fun setupRecyclerView() {
        dailyAdapter = DailyAdapter()
        rvDailyWeather.apply {
            adapter = dailyAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}