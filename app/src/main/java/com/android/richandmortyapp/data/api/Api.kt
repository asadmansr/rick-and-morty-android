package com.android.richandmortyapp.data.api

import com.android.richandmortyapp.data.api.response.CharacterResponse
import retrofit2.Response
import retrofit2.http.GET

interface Api {
    @GET("character")
    suspend fun getCharacters(): Response<CharacterResponse>
}
