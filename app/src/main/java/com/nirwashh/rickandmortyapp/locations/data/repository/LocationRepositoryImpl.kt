package com.nirwashh.rickandmortyapp.locations.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.nirwashh.rickandmortyapp.core.data.local.RickAndMortyDatabase
import com.nirwashh.rickandmortyapp.locations.data.LocationPagingSource
import com.nirwashh.rickandmortyapp.locations.data.model.LocationData
import com.nirwashh.rickandmortyapp.locations.data.model.LocationFilters
import com.nirwashh.rickandmortyapp.locations.data.remote.LocationService
import com.nirwashh.rickandmortyapp.locations.domain.LocationRepository
import kotlinx.coroutines.flow.Flow

class LocationRepositoryImpl(
    private val service: LocationService,
    private val database: RickAndMortyDatabase
) : LocationRepository {
    override suspend fun getLocations(filters: LocationFilters): Flow<PagingData<LocationData>> =
        Pager(
            config = PagingConfig(1),
            pagingSourceFactory = { LocationPagingSource(service, filters) }
        ).flow

    override suspend fun getLocationByName(name: String): List<LocationData> {
        val response = service.fetchLocationByName(name)
        return response.body()!!.results
    }

    override fun getObservableLocationById(id: Int) = service.fetchObservableLocationById(id)
}