package com.nirwashh.rickandmortyapp.episodes.domain

import androidx.paging.PagingData
import com.nirwashh.rickandmortyapp.episodes.domain.model.EpisodeDomain
import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow

interface EpisodesRepository {
    fun getEpisodes(
        name: String?,
        episode: String?
    ): Flow<PagingData<EpisodeDomain>>

    suspend fun getEpisodesByIds(ids: String): List<EpisodeDomain>

    fun getObservableEpisodesByIds(ids: List<Int>): Observable<List<EpisodeDomain>>
}
