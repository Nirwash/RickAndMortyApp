package com.nirwashh.rickandmortyapp.episodes.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.nirwashh.rickandmortyapp.episodes.data.EpisodesPagingSource
import com.nirwashh.rickandmortyapp.episodes.data.model.Episode
import com.nirwashh.rickandmortyapp.episodes.data.model.EpisodeFilters
import com.nirwashh.rickandmortyapp.episodes.data.remote.EpisodesService
import com.nirwashh.rickandmortyapp.episodes.domain.EpisodesRepository
import kotlinx.coroutines.flow.Flow

class EpisodesRepositoryImpl(
    private val episodesService: EpisodesService
) : EpisodesRepository {

    override suspend fun getEpisodes(filters: EpisodeFilters): Flow<PagingData<Episode>> =
        Pager(
            config = PagingConfig(1),
            pagingSourceFactory = { EpisodesPagingSource(episodesService, filters) }
        ).flow

    override suspend fun getEpisodes(ids: String) = episodesService.fetchMultipleEpisodes(ids)

    override suspend fun getEpisode(id: Int) = episodesService.fetchEpisode(id)

}
