package com.example.api

import okhttp3.logging.HttpLoggingInterceptor

class RetrofitInstance {
    companion object{
        private val retrofit by lazy {
            val logging = HttpLoggingInterceptor()
        }
    }
}