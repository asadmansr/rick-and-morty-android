package com.android.richandmortyapp.ui.characters_list

import android.util.Log
import androidx.lifecycle.ViewModel
import com.android.richandmortyapp.data.api.ApiService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CharactersListViewModel : ViewModel() {

    fun getItems() {
        GlobalScope.launch {
            Log.d("rmDebug", "get items")
            val temp = ApiService().create().getCharacters()
            Log.d("rmDebug", temp.body()?.results.toString())
        }
    }
}
