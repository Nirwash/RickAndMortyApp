package com.nirwashh.rickandmortyapp.characters.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.nirwashh.rickandmortyapp.characters.data.model.Character
import com.nirwashh.rickandmortyapp.characters.data.model.CharacterFilters
import com.nirwashh.rickandmortyapp.characters.domain.CharactersInteractor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.stateIn

class CharactersViewModel(
    private val interactor: CharactersInteractor
) : ViewModel() {
    var charactersFlow: Flow<PagingData<Character>> = emptyFlow()
    private var filters = MutableLiveData(CharacterFilters())

    init {
        charactersFlow = filters.asFlow()
            .debounce(750)
            .flatMapConcat {
                interactor.getCharacters(it)
            }
            .cachedIn(viewModelScope)
            .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())
    }

    fun searchQueryListener(searchQuery: String) {
        if (filters.value?.name == searchQuery) return
        else {
            val newFilter = filters.value?.copy(name = searchQuery)
            filters.value = newFilter
        }
    }

    fun refresh() {
        this.filters.postValue(this.filters.value)
    }
}