package com.nirwashh.rickandmortyapp.locations.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.nirwashh.rickandmortyapp.characters.data.model.Character
import com.nirwashh.rickandmortyapp.characters.domain.CharactersInteractor
import com.nirwashh.rickandmortyapp.locations.data.model.Location
import com.nirwashh.rickandmortyapp.locations.data.model.LocationFilters
import com.nirwashh.rickandmortyapp.locations.domain.LocationInteractor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LocationViewModel(
    private val interactor: LocationInteractor,
    private val characterInteractor: CharactersInteractor
) : ViewModel() {
    var locationFlow: Flow<PagingData<Location>> = emptyFlow()
    private val _filterState = MutableStateFlow(LocationFilters())
    val filterState = _filterState.asStateFlow()
    var characters = MutableLiveData<List<Character>>()

    init {
        update()
    }

    fun update() {
        locationFlow = filterState
            .flatMapConcat { interactor.getLocations(it) }
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

    fun updateFilters(filters: LocationFilters) {
        _filterState.update {
            it.copy(
                name = filters.name,
                type = filters.type,
                dimension = filters.dimension
            )
        }
    }

    fun clearFilters() {
        _filterState.update { LocationFilters().copy() }
    }
}