package com.nirwashh.rickandmortyapp.episodes.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.nirwashh.rickandmortyapp.core.data.local.RickAndMortyDatabase
import com.nirwashh.rickandmortyapp.episodes.data.mapper.EpisodeDataToDomain
import com.nirwashh.rickandmortyapp.episodes.data.paging.EpisodeRemoteMediator
import com.nirwashh.rickandmortyapp.episodes.data.remote.EpisodesService
import com.nirwashh.rickandmortyapp.episodes.domain.EpisodesRepository
import com.nirwashh.rickandmortyapp.episodes.domain.model.EpisodeDomain
import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class EpisodesRepositoryImpl(
    private val episodesService: EpisodesService,
    private val database: RickAndMortyDatabase,
    private val episodeDataToDomain: EpisodeDataToDomain
) : EpisodesRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getEpisodes(
        name: String?,
        episode: String?
    ): Flow<PagingData<EpisodeDomain>> {

        val pagingSourceFactory =
            {
                database.episodeDao().getPagedEpisodes(
                    name = name,
                    episode = episode
                )
            }
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = PagingConfig.MAX_SIZE_UNBOUNDED,
                jumpThreshold = Int.MIN_VALUE,
            ),
            remoteMediator = EpisodeRemoteMediator(
                service = episodesService,
                database = database,
                name = name,
                episode = episode
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow.map { pagingData ->
            pagingData.map { episode ->
                episodeDataToDomain.map(episode)
            }
        }
    }

    override suspend fun getEpisodesByIds(ids: String) =
        episodesService.fetchMultipleEpisodes(ids).map { episodeDataToDomain.map(it) }

    override fun getObservableEpisodesByIds(ids: List<Int>): Observable<List<EpisodeDomain>> {
        val episodes = episodesService.fetchObservableMultipleEpisodes(ids.toString())
        return episodes.map { list ->
            list.map { episode ->
                episodeDataToDomain.map(episode)
            }
        }
    }

}
