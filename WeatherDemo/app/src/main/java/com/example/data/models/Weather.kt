package com.example.data.models

data class Weather(
    val description: String,
    val icon: String? = null,
    val id: Int? = null,
    val main: String
)