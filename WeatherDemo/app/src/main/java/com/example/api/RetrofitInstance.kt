package com.example.api

import com.example.util.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object{
        private val retrofit by lazy {
            // Used for additional Debugging of HTTP requests
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build()
            // Build the Retrofit object
            Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    // Google defined converter of responses to JSON
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
        }

        // The API object available to use to make network requests
        val api by lazy {
            retrofit.create(OpenWeatherAPI::class.java)
        }
    }
}