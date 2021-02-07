package com.android.richandmortyapp.ui.characters_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CharactersListViewModelFactory : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CharactersListViewModel() as T
    }
}
