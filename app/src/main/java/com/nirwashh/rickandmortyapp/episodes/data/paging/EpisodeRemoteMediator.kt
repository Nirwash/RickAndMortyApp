package com.nirwashh.rickandmortyapp.episodes.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.nirwashh.rickandmortyapp.core.data.local.RickAndMortyDatabase
import com.nirwashh.rickandmortyapp.episodes.data.model.EpisodeData
import com.nirwashh.rickandmortyapp.episodes.data.model.EpisodeKeys
import com.nirwashh.rickandmortyapp.episodes.data.remote.EpisodesService
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class EpisodeRemoteMediator(
    private val service: EpisodesService,
    private val database: RickAndMortyDatabase,
    private val name: String?,
    private val episode: String?
) : RemoteMediator<Int, EpisodeData>() {

    private val dao = database.episodeDao()
    private val keyDao = database.episodesKeyDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, EpisodeData>
    ): MediatorResult {


        val page = when (val pageKeyData = getKeyPageData(loadType, state)) {
            is MediatorResult.Success -> return pageKeyData
            else -> pageKeyData as Int
        }

        try {

            val response =
                service.fetchEpisodes(
                    page = page,
                    name = name,
                    episode = episode
                )

            val isEndOfList =
                response.info.next == null
                        || response.toString().contains("error")
                        || response.results.isEmpty()

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    dao.deleteEpisodes()
                    keyDao.deleteKeys()
                }
                val prevKey = if (page == 1) null else page - 1
                val nextKey = if (isEndOfList) null else page + 1
                val keys = response.results.map {
                    EpisodeKeys(it.id, prevPage = prevKey, nextPage = nextKey)
                }
                keyDao.insertKeys(remoteKeysEpisodes = keys)
                dao.insertEpisodes(episodes = response.results)
            }
            return MediatorResult.Success(endOfPaginationReached = isEndOfList)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, EpisodeData>
    ): EpisodeKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                keyDao.getKeys(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, EpisodeData>
    ): EpisodeKeys? {
        return state.pages
            .firstOrNull { it.data.isNotEmpty() }
            ?.data?.firstOrNull()
            ?.let {
                keyDao.getKeys(id = it.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, EpisodeData>
    ): EpisodeKeys? {
        return state.pages
            .lastOrNull { it.data.isNotEmpty() }
            ?.data?.lastOrNull()
            ?.let {
                keyDao.getKeys(id = it.id)
            }
    }

    private suspend fun getKeyPageData(
        loadType: LoadType,
        state: PagingState<Int, EpisodeData>
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
                return nextPage ?: MediatorResult.Success(
                    endOfPaginationReached = remoteKeys != null
                )
            }
        }
    }
}