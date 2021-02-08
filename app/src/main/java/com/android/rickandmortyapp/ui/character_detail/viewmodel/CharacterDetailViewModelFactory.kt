package com.android.rickandmortyapp.ui.character_detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.rickandmortyapp.data.repository.character.CharacterRepository
import com.android.rickandmortyapp.data.repository.episode.EpisodeRepository

class CharacterDetailViewModelFactory(
    private val characterRepository: CharacterRepository,
    private val episodeRepository: EpisodeRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CharacterDetailViewModel(characterRepository, episodeRepository) as T
    }
}
