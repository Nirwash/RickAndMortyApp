package com.nirwashh.rickandmortyapp.locations.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.nirwashh.rickandmortyapp.core.data.local.RickAndMortyDatabase
import com.nirwashh.rickandmortyapp.locations.data.model.LocationData
import com.nirwashh.rickandmortyapp.locations.data.model.LocationKeys
import com.nirwashh.rickandmortyapp.locations.data.remote.LocationService
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class LocationRemoteMediator(
    private val service: LocationService,
    private val database: RickAndMortyDatabase,
    private val name: String?,
    private val type: String?,
    private val dimension: String?
) : RemoteMediator<Int, LocationData>() {

    private val dao = database.locationDao()
    private val keydao = database.locationsKeyDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, LocationData>
    ): MediatorResult {


        val page = when (val pageKeyData = getKeyPageData(loadType, state)) {
            is MediatorResult.Success -> return pageKeyData
            else -> pageKeyData as Int
        }

        try {

            val response =
                service.fetchLocations(
                    page = page,
                    name = name,
                    type = type,
                    dimension = dimension
                )

            val isEndOfList =
                response.info.next == null
                        || response.toString().contains("error")
                        || response.results.isEmpty()

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    dao.deleteLocations()
                    keydao.deleteKeys()
                }
                val prevKey = if (page == 1) null else page - 1
                val nextKey = if (isEndOfList) null else page + 1
                val keys = response.results.map {
                    LocationKeys(it.id, prevPage = prevKey, nextPage = nextKey)
                }
                keydao.insertKeys(remoteKeysLocations = keys)
                dao.insertLocations(locations = response.results)
            }
            return MediatorResult.Success(endOfPaginationReached = isEndOfList)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, LocationData>
    ): LocationKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                keydao.getKeys(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, LocationData>
    ): LocationKeys? {
        return state.pages
            .firstOrNull { it.data.isNotEmpty() }
            ?.data?.firstOrNull()
            ?.let { it ->
                keydao.getKeys(id = it.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, LocationData>
    ): LocationKeys? {
        return state.pages
            .lastOrNull { it.data.isNotEmpty() }
            ?.data?.lastOrNull()
            ?.let { it ->
                keydao.getKeys(id = it.id)
            }
    }

    private suspend fun getKeyPageData(
        loadType: LoadType,
        state: PagingState<Int, LocationData>
    ): Any {
        return when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextPage?.minus(1) ?: 1
            }

            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                remoteKeys?.prevPage ?: return MediatorResult.Success(
                    endOfPaginationReached = remoteKeys != null
                )
            }

            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextPage = remoteKeys?.nextPage
                return nextPage ?: RemoteMediator.MediatorResult.Success(
                    endOfPaginationReached = remoteKeys != null
                )
            }
        }
    }
}