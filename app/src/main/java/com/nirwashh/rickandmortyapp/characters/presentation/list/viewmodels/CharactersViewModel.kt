package com.nirwashh.rickandmortyapp.characters.presentation.list.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.nirwashh.rickandmortyapp.characters.data.model.Character
import com.nirwashh.rickandmortyapp.characters.data.model.CharacterFilters
import com.nirwashh.rickandmortyapp.characters.domain.CharactersInteractor
import com.nirwashh.rickandmortyapp.core.utils.update
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapConcat

class CharactersViewModel(
    private val interactor: CharactersInteractor
) : ViewModel() {
    var charactersFlow: Flow<PagingData<Character>> = emptyFlow()
    private val _filterState = MutableStateFlow(CharacterFilters())
    val filterState = _filterState.asStateFlow()

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