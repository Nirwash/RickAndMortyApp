package com.nirwashh.rickandmortyapp.characters.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nirwashh.rickandmortyapp.characters.domain.CharactersInteractor
import com.nirwashh.rickandmortyapp.episodes.domain.EpisodesInteractor
import javax.inject.Inject

class CharactersViewModelFactory @Inject constructor(
    private val interactor: CharactersInteractor,
    private val episodesInteractor: EpisodesInteractor
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CharactersViewModel(
            interactor, episodesInteractor
        ) as T
    }
}
