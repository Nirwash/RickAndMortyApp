package com.nirwashh.rickandmortyapp.episodes.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nirwashh.rickandmortyapp.core.utils.pageParser
import com.nirwashh.rickandmortyapp.episodes.data.model.Episode
import com.nirwashh.rickandmortyapp.episodes.data.model.EpisodeFilters
import com.nirwashh.rickandmortyapp.episodes.data.remote.EpisodesService

class EpisodesPagingSource(
    private val episodesService: EpisodesService,
    private val filters: EpisodeFilters
) : PagingSource<Int, Episode>() {
    override fun getRefreshKey(state: PagingState<Int, Episode>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Episode> {
        val pageIndex = params.key ?: 0
        return try {
            val response = episodesService.fetchEpisodes(
                name = filters.name,
                episode = filters.episode,
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

