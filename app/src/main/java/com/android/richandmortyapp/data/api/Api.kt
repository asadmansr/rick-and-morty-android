package com.android.richandmortyapp.data.api

import com.android.richandmortyapp.data.api.response.character.CharacterResponse
import com.android.richandmortyapp.data.api.response.character.Result
import com.android.richandmortyapp.data.api.response.episode.EpisodeResponse
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
