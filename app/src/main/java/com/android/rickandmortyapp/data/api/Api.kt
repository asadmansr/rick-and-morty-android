package com.android.rickandmortyapp.data.api

import com.android.rickandmortyapp.data.api.response.character.CharacterResponse
import com.android.rickandmortyapp.data.api.response.character.Result
import com.android.rickandmortyapp.data.api.response.episode.EpisodeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("character")
    suspend fun getCharacters(): Response<CharacterResponse>

    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): Response<Result>

    @GET("episode/{episodes}")
    suspend fun getEpisodes(@Path("episodes") episodes: String): Response<EpisodeResponse>
}
