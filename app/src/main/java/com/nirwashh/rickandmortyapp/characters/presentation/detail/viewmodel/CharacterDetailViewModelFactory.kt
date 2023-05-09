package com.nirwashh.rickandmortyapp.characters.presentation.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nirwashh.rickandmortyapp.episodes.domain.EpisodesInteractor
import com.nirwashh.rickandmortyapp.locations.domain.LocationInteractor

class CharacterDetailViewModelFactory(
    private val episodesInteractor: EpisodesInteractor,
    private val locationInteractor: LocationInteractor
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CharacterDetailViewModel(episodesInteractor, locationInteractor) as T
    }
}