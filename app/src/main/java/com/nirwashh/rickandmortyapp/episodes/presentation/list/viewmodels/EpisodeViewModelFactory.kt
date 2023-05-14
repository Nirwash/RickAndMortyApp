package com.nirwashh.rickandmortyapp.episodes.presentation.list.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nirwashh.rickandmortyapp.episodes.domain.EpisodesInteractor
import com.nirwashh.rickandmortyapp.episodes.presentation.mapper.EpisodeDomainToUi

class EpisodeViewModelFactory(
    private val episodesInteractor: EpisodesInteractor,
    private val episodeDomainToUi: EpisodeDomainToUi
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EpisodesViewModel(
            episodesInteractor, episodeDomainToUi
        ) as T
    }
}
