package com.nirwashh.rickandmortyapp.episodes.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.nirwashh.rickandmortyapp.characters.data.model.Character
import com.nirwashh.rickandmortyapp.characters.domain.CharactersInteractor
import com.nirwashh.rickandmortyapp.core.utils.update
import com.nirwashh.rickandmortyapp.episodes.data.model.Episode
import com.nirwashh.rickandmortyapp.episodes.data.model.EpisodeFilters
import com.nirwashh.rickandmortyapp.episodes.domain.EpisodesInteractor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.launch

class EpisodesViewModel(
    private val episodesInteractor: EpisodesInteractor,
    private val characterInteractor: CharactersInteractor
) : ViewModel() {
    var episodesFlow: Flow<PagingData<Episode>> = emptyFlow()
    private val _filterState = MutableStateFlow(EpisodeFilters())
    val filterState = _filterState.asStateFlow()
    var characters = MutableLiveData<List<Character>>()

    init {
        update()
    }

    fun update() {
        episodesFlow = filterState
            .flatMapConcat {
                episodesInteractor.getEpisodes(it)
            }
            .cachedIn(viewModelScope)
    }

    fun setCharacters(ids: String) {
        viewModelScope.launch {
            characters.value = characterInteractor.getCharactersByIds(ids)
        }
    }

    fun getCharacter(id: Int): Character {
        return characters.value?.find { it.id == id }!!
    }

    fun updateFilters(filters: EpisodeFilters) {
        _filterState.update {
            it.copy(
                name = filters.name,
                episode = filters.episode
            )
        }
    }

    fun clearFilters() {
        _filterState.update { EpisodeFilters().copy() }
    }
}
