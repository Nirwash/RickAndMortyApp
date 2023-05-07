package com.nirwashh.rickandmortyapp.locations.domain

import com.nirwashh.rickandmortyapp.locations.data.model.LocationFilters

class LocationInteractor(private val repository: LocationRepository) {
    suspend fun getLocations(filters: LocationFilters) = repository.getLocations(filters)

    suspend fun getLocationById(id: Int) =
        repository.getLocationsById(id)

    suspend fun getLocationByName(name: String) = repository.getLocationByName(name)
}