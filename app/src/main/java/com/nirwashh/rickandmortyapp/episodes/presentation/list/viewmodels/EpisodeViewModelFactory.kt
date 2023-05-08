package com.nirwashh.rickandmortyapp.episodes.presentation.list.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nirwashh.rickandmortyapp.episodes.domain.EpisodesInteractor

class EpisodeViewModelFactory(
    private val episodesInteractor: EpisodesInteractor
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EpisodesViewModel(
            episodesInteractor
        ) as T
    }
}
