package com.android.rickandmortyapp.ui.characters_list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.rickandmortyapp.data.repository.character.CharacterRepository

class CharactersListViewModelFactory(
    private val characterRepository: CharacterRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CharactersListViewModel(characterRepository) as T
    }
}
