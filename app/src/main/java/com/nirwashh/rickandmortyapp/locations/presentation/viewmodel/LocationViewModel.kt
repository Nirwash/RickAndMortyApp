package com.nirwashh.rickandmortyapp.locations.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.nirwashh.rickandmortyapp.locations.data.model.Location
import com.nirwashh.rickandmortyapp.locations.data.model.LocationFilters
import com.nirwashh.rickandmortyapp.locations.domain.LocationInteractor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.update

class LocationViewModel(
    private val interactor: LocationInteractor
) : ViewModel() {
    var locationFlow: Flow<PagingData<Location>> = emptyFlow()
    private val _filterState = MutableStateFlow(LocationFilters())
    val filterState = _filterState.asStateFlow()

    init {
        update()
    }

    fun update() {
        locationFlow = filterState
            .flatMapConcat { interactor.getLocations(it) }
            .cachedIn(viewModelScope)
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