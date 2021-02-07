package com.android.richandmortyapp.ui.characters_list.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.richandmortyapp.data.api.ApiService
import com.android.richandmortyapp.data.api.response.Result
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
