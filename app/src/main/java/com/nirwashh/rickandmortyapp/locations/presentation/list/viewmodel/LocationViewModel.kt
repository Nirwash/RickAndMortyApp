package com.nirwashh.rickandmortyapp.locations.presentation.list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.map
import com.nirwashh.rickandmortyapp.locations.domain.LocationInteractor
import com.nirwashh.rickandmortyapp.locations.presentation.mapper.LocationDomainToUi
import com.nirwashh.rickandmortyapp.locations.presentation.model.LocationUi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class LocationViewModel(
    private val interactor: LocationInteractor,
    private val mapper: LocationDomainToUi
) : ViewModel() {
    val locationFlow = MutableSharedFlow<PagingData<LocationUi>>()
    val filters = MutableStateFlow<MutableMap<String, String?>>(
        mutableMapOf(
            "name" to null,
            "type" to null,
            "dimension" to null
        )
    )

    fun load(
        name: String?,
        type: String?,
        dimension: String?
    ) {
        interactor.getLocations(name, type, dimension).onEach {
            locationFlow.emit(
                it.map { location -> mapper.map(location) }
            )
        }.launchIn(viewModelScope)
    }
}