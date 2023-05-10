package com.nirwashh.rickandmortyapp.locations.domain

import androidx.paging.PagingData
import com.nirwashh.rickandmortyapp.locations.data.model.Location
import com.nirwashh.rickandmortyapp.locations.data.model.LocationFilters
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    suspend fun getLocations(filters: LocationFilters): Flow<PagingData<Location>>
    suspend fun getLocationByName(name: String): List<Location>
    fun getObservableLocationById(ids: Int): Single<Location>
}