package com.android.richandmortyapp.ui.characters_list

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CharactersListViewModel : ViewModel() {

    fun getItems() {
        GlobalScope.launch {
            Log.d("rmDebug", "get items")
        }
    }
}
