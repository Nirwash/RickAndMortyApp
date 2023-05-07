package com.nirwashh.rickandmortyapp.locations.domain

import androidx.paging.PagingData
import com.nirwashh.rickandmortyapp.locations.data.model.Location
import com.nirwashh.rickandmortyapp.locations.data.model.LocationFilters
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    suspend fun getLocations(filters: LocationFilters): Flow<PagingData<Location>>
    suspend fun getLocationsById(id: Int): Location
    suspend fun getLocationByName(name: String): List<Location>
}