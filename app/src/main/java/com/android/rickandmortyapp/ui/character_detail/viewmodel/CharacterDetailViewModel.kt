package com.android.rickandmortyapp.ui.character_detail.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.rickandmortyapp.data.api.ApiService
import com.android.rickandmortyapp.data.api.response.character.Result
import com.android.rickandmortyapp.data.api.response.episode.EpisodeResponse
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CharacterDetailViewModel : ViewModel() {

    val character = MutableLiveData<Result>()
    val episodes = MutableLiveData<EpisodeResponse>()

    fun getCharacterById(id: Int) {
        GlobalScope.launch {
            Log.d("rmDebug", "get items detail")
            character.postValue(ApiService().create().getCharacterById(id).body())
        }
    }

    fun getEpisodesOfCharacter(episodeQuery: String) {
        GlobalScope.launch {
            Log.d("rmDebug", "get episodes detail")
            episodes.postValue(ApiService().create().getEpisodes(episodeQuery).body())
        }
    }
}
