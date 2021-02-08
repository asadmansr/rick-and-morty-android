package com.android.rickandmortyapp.ui.characters_list.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.rickandmortyapp.data.api.response.character.Result
import com.android.rickandmortyapp.data.repository.character.CharacterRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CharactersListViewModel(
    private val characterRepository: CharacterRepository
) : ViewModel() {

    val characters = MutableLiveData<List<Result>>()

    fun getCharacters() {
        GlobalScope.launch {
            val result = characterRepository.getCharacters()
            if (result.isSuccessful) {
                characters.postValue(result.body()?.results)
            } else {
                characters.postValue(null)
            }
        }
    }
}
