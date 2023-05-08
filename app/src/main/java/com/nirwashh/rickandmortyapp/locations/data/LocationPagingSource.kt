package com.nirwashh.rickandmortyapp.locations.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nirwashh.rickandmortyapp.core.utils.pageParser
import com.nirwashh.rickandmortyapp.locations.data.model.Location
import com.nirwashh.rickandmortyapp.locations.data.model.LocationFilters
import com.nirwashh.rickandmortyapp.locations.data.remote.LocationService

class LocationPagingSource(
    private val service: LocationService,
    private val filters: LocationFilters
) : PagingSource<Int, Location>() {
    override fun getRefreshKey(state: PagingState<Int, Location>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Location> {
        val pageIndex = params.key ?: 0
        return try {
            val response = service.fetchLocations(
                name = filters.name,
                type = filters.type,
                dimension = filters.dimension,
                pageIndex
            )
            val data = checkNotNull(response.body()).results
            val prevKey = if (pageIndex == 0) null else pageIndex - 1
            val nextKey = response.body()?.info?.next?.pageParser()
            return LoadResult.Page(data, prevKey, nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}