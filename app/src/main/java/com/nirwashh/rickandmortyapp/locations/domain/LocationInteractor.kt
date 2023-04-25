package com.nirwashh.rickandmortyapp.locations.domain

import com.nirwashh.rickandmortyapp.locations.data.model.LocationFilters
import javax.inject.Inject

class LocationInteractor @Inject constructor(private val repository: LocationRepository) {
    suspend fun getLocations(filters: LocationFilters) = repository.getLocations(filters)
}