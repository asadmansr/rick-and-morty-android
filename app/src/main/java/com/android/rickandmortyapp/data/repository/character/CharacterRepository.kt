package com.android.rickandmortyapp.data.repository.character

import com.android.rickandmortyapp.data.api.response.character.CharacterResponse
import com.android.rickandmortyapp.data.api.response.character.Result
import retrofit2.Response

interface CharacterRepository {

    suspend fun getCharacters(): Response<CharacterResponse>

    suspend fun getCharacterById(id: Int): Response<Result>
}
