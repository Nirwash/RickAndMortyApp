package com.nirwashh.rickandmortyapp.episodes.domain

import androidx.paging.PagingData
import com.nirwashh.rickandmortyapp.episodes.data.model.Episode
import com.nirwashh.rickandmortyapp.episodes.data.model.EpisodeFilters
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow

interface EpisodesRepository {
    suspend fun getEpisodes(filters: EpisodeFilters): Flow<PagingData<Episode>>
    suspend fun getEpisodesByIds(ids: String): List<Episode>
    fun getObservableEpisodesByIds(ids: String): Single<List<Episode>>
}
