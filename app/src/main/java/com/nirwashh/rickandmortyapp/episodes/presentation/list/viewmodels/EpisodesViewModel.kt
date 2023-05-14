package com.nirwashh.rickandmortyapp.episodes.presentation.list.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.map
import com.nirwashh.rickandmortyapp.episodes.domain.EpisodesInteractor
import com.nirwashh.rickandmortyapp.episodes.presentation.mapper.EpisodeDomainToUi
import com.nirwashh.rickandmortyapp.episodes.presentation.model.EpisodeUi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class EpisodesViewModel(
    private val interactor: EpisodesInteractor,
    private val mapper: EpisodeDomainToUi
) : ViewModel() {
    val episodesFlow = MutableSharedFlow<PagingData<EpisodeUi>>()
    val filters = MutableStateFlow<MutableMap<String, String?>>(
        mutableMapOf(
            "name" to null,
            "episode" to null
        )
    )

    fun load(
        name: String?,
        episode: String?
    ) {
        interactor.getEpisodes(
            name, episode
        ).onEach {
            episodesFlow.emit(
                it.map { character -> mapper.map(character) }
            )
        }.launchIn(viewModelScope)
    }
}
