package com.android.richandmortyapp.data.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiService {

    private val baseUrl = "https://rickandmortyapi.com/api/"

    fun create(): Api {
        val httpClient = OkHttpClient.Builder()
                .build()

        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build()
                .create(Api::class.java)
    }
}
