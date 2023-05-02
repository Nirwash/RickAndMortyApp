package com.nirwashh.rickandmortyapp.episodes.domain

import androidx.paging.PagingData
import com.nirwashh.rickandmortyapp.episodes.data.model.Episode
import com.nirwashh.rickandmortyapp.episodes.data.model.EpisodeFilters
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface EpisodesRepository {
    suspend fun getEpisodes(filters: EpisodeFilters): Flow<PagingData<Episode>>
    suspend fun getEpisodesByIds(ids: String): List<Episode>
    suspend fun getEpisodeById(id: Int): Response<Episode>
}
