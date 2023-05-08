package com.nirwashh.rickandmortyapp.locations.presentation.list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nirwashh.rickandmortyapp.locations.domain.LocationInteractor

class LocationViewModelFactory(
    private val interactor: LocationInteractor
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LocationViewModel(interactor) as T
    }
}