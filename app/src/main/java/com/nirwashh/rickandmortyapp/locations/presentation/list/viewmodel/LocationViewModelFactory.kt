package com.nirwashh.rickandmortyapp.locations.presentation.list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nirwashh.rickandmortyapp.locations.domain.LocationInteractor
import com.nirwashh.rickandmortyapp.locations.presentation.mapper.LocationDomainToUi

class LocationViewModelFactory(
    private val interactor: LocationInteractor,
    private val mapper: LocationDomainToUi
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LocationViewModel(interactor, mapper) as T
    }
}