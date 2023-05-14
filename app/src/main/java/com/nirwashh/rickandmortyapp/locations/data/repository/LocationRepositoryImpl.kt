package com.nirwashh.rickandmortyapp.locations.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.nirwashh.rickandmortyapp.core.data.local.RickAndMortyDatabase
import com.nirwashh.rickandmortyapp.locations.data.mapper.LocationDataToDomain
import com.nirwashh.rickandmortyapp.locations.data.paging.LocationRemoteMediator
import com.nirwashh.rickandmortyapp.locations.data.remote.LocationService
import com.nirwashh.rickandmortyapp.locations.domain.LocationRepository
import com.nirwashh.rickandmortyapp.locations.domain.model.LocationDomain
import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocationRepositoryImpl(
    private val locationService: LocationService,
    private val database: RickAndMortyDatabase,
    private val locationDataToDomain: LocationDataToDomain
) : LocationRepository {
    @OptIn(ExperimentalPagingApi::class)
    override fun getLocations(
        name: String?,
        type: String?,
        dimension: String?
    ): Flow<PagingData<LocationDomain>> {

        val pagingSourceFactory =
            {
                database.locationDao().getPagedLocations(
                    name, type, dimension
                )
            }
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = PagingConfig.MAX_SIZE_UNBOUNDED,
                jumpThreshold = Int.MIN_VALUE,
            ),
            remoteMediator = LocationRemoteMediator(
                service = locationService,
                database = database,
                name = name,
                type = type,
                dimension = dimension
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow.map { pagingData ->
            pagingData.map { location ->
                locationDataToDomain.map(location)
            }
        }
    }


    override suspend fun getLocationByName(name: String): List<LocationDomain> {
        val response = locationService.fetchLocationByName(name)
        return response.body()!!.results.map { locationDataToDomain.map(it) }
    }

    override fun getObservableLocationById(id: Int): Observable<LocationDomain> {
        val result = locationService.fetchObservableLocationById(id)
        return result.map { location ->
            locationDataToDomain.map(location)
        }
    }
}