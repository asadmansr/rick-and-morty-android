package com.android.rickandmortyapp.data.repository.character

import com.android.rickandmortyapp.data.api.ApiService
import com.android.rickandmortyapp.data.api.response.character.CharacterResponse
import com.android.rickandmortyapp.data.api.response.character.Result
import com.android.rickandmortyapp.data.repository.character.CharacterRepository
import retrofit2.Response

class CharacterRepositoryImpl(
    private val apiService: ApiService
) : CharacterRepository {

    override suspend fun getCharacters(): Response<CharacterResponse> = apiService.getCharacters()

    override suspend fun getCharacterById(id: Int): Response<Result> =
        apiService.getCharacterById(id)
}
