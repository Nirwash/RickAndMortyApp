package com.nirwashh.rickandmortyapp.episodes.presentation.detail.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nirwashh.rickandmortyapp.characters.domain.CharactersInteractor

class EpisodeDetailViewModelFactory(
    private val charactersInteractor: CharactersInteractor
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EpisodeDetailViewModel(charactersInteractor) as T
    }
}