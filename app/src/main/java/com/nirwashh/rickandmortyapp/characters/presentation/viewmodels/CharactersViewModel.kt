package com.nirwashh.rickandmortyapp.characters.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.nirwashh.rickandmortyapp.characters.data.model.Character
import com.nirwashh.rickandmortyapp.characters.data.model.CharacterFilters
import com.nirwashh.rickandmortyapp.characters.domain.CharactersInteractor
import com.nirwashh.rickandmortyapp.core.utils.update
import com.nirwashh.rickandmortyapp.episodes.data.model.Episode
import com.nirwashh.rickandmortyapp.episodes.domain.EpisodesInteractor
import com.nirwashh.rickandmortyapp.locations.data.model.Location
import com.nirwashh.rickandmortyapp.locations.domain.LocationInteractor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.launch

class CharactersViewModel(
    private val interactor: CharactersInteractor,
    private val episodesInteractor: EpisodesInteractor,
    private val locationInteractor: LocationInteractor
) : ViewModel() {
    var charactersFlow: Flow<PagingData<Character>> = emptyFlow()
    private val _filterState = MutableStateFlow(CharacterFilters())
    val filterState = _filterState.asStateFlow()
    var episodes = MutableLiveData<List<Episode>>()
    var locations = MutableLiveData<List<Location>>()


    init {
        update()
    }

    fun update() {
        charactersFlow = filterState
            .flatMapConcat {
                interactor.getCharacters(it)
            }
            .cachedIn(viewModelScope)
    }

    fun setEpisodes(ids: String) {
        viewModelScope.launch {
            episodes.value = episodesInteractor.getEpisodesByIds(ids)
        }
    }

    fun setLocations(location: Location, origin: Location) {
        if (location.name != "unknown") {
            viewModelScope.launch {
                locations.value = locationInteractor.getLocationByName(location.name)
            }
        }
        if (origin.name != "unknown") {
            viewModelScope.launch {
                locations.value = locationInteractor.getLocationByName(origin.name)
            }
        }

    }

    fun getEpisode(id: Int): Episode {
        return episodes.value?.find { it.id == id }!!
    }

    fun getLocation(name: String): Location {
        return locations.value?.find { it.name == name }!!
    }

    fun updateFilters(filters: CharacterFilters) {
        _filterState.update {
            it.copy(
                name = filters.name,
                status = filters.status,
                species = filters.species,
                type = filters.type,
                gender = filters.gender
            )
        }
    }

    fun clearFilters() {
        _filterState.update { CharacterFilters().copy() }
    }
}