package com.android.rickandmortyapp.ui.characters_list.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.rickandmortyapp.data.api.ApiService
import com.android.rickandmortyapp.data.api.response.character.Result
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CharactersListViewModel : ViewModel() {

    val characters = MutableLiveData<List<Result>>()

    fun getCharacters() {
        GlobalScope.launch {
            Log.d("rmDebug", "get items")
            characters.postValue(ApiService().create().getCharacters().body()?.results)
        }
    }
}
