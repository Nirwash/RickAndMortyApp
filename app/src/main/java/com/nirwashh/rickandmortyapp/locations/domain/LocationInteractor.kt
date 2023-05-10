package com.nirwashh.rickandmortyapp.locations.domain

import com.nirwashh.rickandmortyapp.locations.data.model.LocationFilters

class LocationInteractor(private val repository: LocationRepository) {
    suspend fun getLocations(filters: LocationFilters) = repository.getLocations(filters)
    suspend fun getLocationByName(name: String) = repository.getLocationByName(name)
    fun getObservableLocationById(id: Int) = repository.getObservableLocationById(id)
}