package com.nirwashh.rickandmortyapp.locations.domain

import androidx.paging.PagingData
import com.nirwashh.rickandmortyapp.locations.data.model.LocationData
import com.nirwashh.rickandmortyapp.locations.data.model.LocationFilters
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    suspend fun getLocations(filters: LocationFilters): Flow<PagingData<LocationData>>
    suspend fun getLocationByName(name: String): List<LocationData>
    fun getObservableLocationById(ids: Int): Single<LocationData>
}