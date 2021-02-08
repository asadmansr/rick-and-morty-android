package com.android.rickandmortyapp.data.api

import com.android.rickandmortyapp.data.api.response.character.CharacterResponse
import com.android.rickandmortyapp.data.api.response.character.Result
import com.android.rickandmortyapp.data.api.response.episode.EpisodeResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("character")
    suspend fun getCharacters(): Response<CharacterResponse>

    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): Response<Result>

    @GET("episode/{episodes}")
    suspend fun getEpisodes(@Path("episodes") episodes: String): Response<EpisodeResponse>

    companion object {
        operator fun invoke(): ApiService {
            val httpClient = OkHttpClient.Builder()
                .build()

            return Retrofit.Builder()
                .baseUrl("https://rickandmortyapi.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build()
                .create(ApiService::class.java)
        }
    }
}