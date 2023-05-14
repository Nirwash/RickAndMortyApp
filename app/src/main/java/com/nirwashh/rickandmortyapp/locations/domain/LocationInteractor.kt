package com.nirwashh.rickandmortyapp.locations.domain

class LocationInteractor(private val repository: LocationRepository) {
    fun getLocations(
        name: String?,
        type: String?,
        dimension: String?
    ) = repository.getLocations(name, type, dimension)

    fun getObservableLocationById(id: Int) = repository.getObservableLocationById(id)
}