package com.nirwashh.rickandmortyapp.locations.domain

import androidx.paging.PagingData
import com.nirwashh.rickandmortyapp.locations.domain.model.LocationDomain
import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    fun getLocations(
        name: String?,
        type: String?,
        dimension: String?
    ): Flow<PagingData<LocationDomain>>

    suspend fun getLocationByName(name: String): List<LocationDomain>
    fun getObservableLocationById(ids: Int): Observable<LocationDomain>
}