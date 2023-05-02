package com.nirwashh.rickandmortyapp.episodes.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nirwashh.rickandmortyapp.characters.domain.CharactersInteractor
import com.nirwashh.rickandmortyapp.episodes.domain.EpisodesInteractor
import javax.inject.Inject

class EpisodeViewModelFactory @Inject constructor(
    private val episodesInteractor: EpisodesInteractor,
    private val charactersInteractor: CharactersInteractor
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EpisodesViewModel(
            episodesInteractor, charactersInteractor
        ) as T
    }
}
