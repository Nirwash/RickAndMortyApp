package com.nirwashh.rickandmortyapp.locations.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.nirwashh.rickandmortyapp.core.data.local.RickAndMortyDao
import com.nirwashh.rickandmortyapp.locations.data.LocationPagingSource
import com.nirwashh.rickandmortyapp.locations.data.model.Location
import com.nirwashh.rickandmortyapp.locations.data.model.LocationFilters
import com.nirwashh.rickandmortyapp.locations.data.remote.LocationService
import com.nirwashh.rickandmortyapp.locations.domain.LocationRepository
import kotlinx.coroutines.flow.Flow

class LocationRepositoryImpl(
    private val service: LocationService,
    private val dao: RickAndMortyDao
) : LocationRepository {
    override suspend fun getLocations(filters: LocationFilters): Flow<PagingData<Location>> =
        Pager(
            config = PagingConfig(1),
            pagingSourceFactory = { LocationPagingSource(service, filters) }
        ).flow
}