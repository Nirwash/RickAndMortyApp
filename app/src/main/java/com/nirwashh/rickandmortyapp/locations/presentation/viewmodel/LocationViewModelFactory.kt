package com.nirwashh.rickandmortyapp.locations.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nirwashh.rickandmortyapp.locations.domain.LocationInteractor
import javax.inject.Inject

class LocationViewModelFactory @Inject constructor(
    private val interactor: LocationInteractor
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LocationViewModel(interactor) as T
    }
}