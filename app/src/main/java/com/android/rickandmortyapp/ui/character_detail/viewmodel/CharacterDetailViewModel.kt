package com.android.rickandmortyapp.ui.character_detail.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.rickandmortyapp.data.api.response.character.Result
import com.android.rickandmortyapp.data.api.response.episode.EpisodeResponse
import com.android.rickandmortyapp.data.repository.character.CharacterRepository
import com.android.rickandmortyapp.data.repository.episode.EpisodeRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CharacterDetailViewModel(
    private val characterRepository: CharacterRepository,
    private val episodeRepository: EpisodeRepository
) : ViewModel() {

    val character = MutableLiveData<Result>()
    val episodes = MutableLiveData<EpisodeResponse>()

    fun getCharacterById(id: Int) {
        GlobalScope.launch {
            val result = characterRepository.getCharacterById(id)
            if (result.isSuccessful) {
                character.postValue(result.body())
            } else {
                character.postValue(null)
            }
        }
    }

    fun getEpisodesOfCharacter(episodeQuery: String) {
        GlobalScope.launch {
            val result = episodeRepository.getEpisodesOfCharacter(episodeQuery)
            if (result.isSuccessful) {
                episodes.postValue(result.body())
            } else {
                episodes.postValue(null)
            }
        }
    }
}
