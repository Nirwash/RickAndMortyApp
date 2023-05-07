package com.nirwashh.rickandmortyapp.characters.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nirwashh.rickandmortyapp.characters.domain.CharactersInteractor
import com.nirwashh.rickandmortyapp.episodes.domain.EpisodesInteractor
import com.nirwashh.rickandmortyapp.locations.domain.LocationInteractor
import javax.inject.Inject

class CharactersViewModelFactory @Inject constructor(
    private val interactor: CharactersInteractor,
    private val episodesInteractor: EpisodesInteractor,
    private val locationInteractor: LocationInteractor
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CharactersViewModel(
            interactor, episodesInteractor, locationInteractor
        ) as T
    }
}
