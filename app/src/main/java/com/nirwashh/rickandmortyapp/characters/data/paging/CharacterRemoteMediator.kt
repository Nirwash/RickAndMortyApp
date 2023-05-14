package com.nirwashh.rickandmortyapp.characters.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.nirwashh.rickandmortyapp.characters.data.model.CharacterData
import com.nirwashh.rickandmortyapp.characters.data.model.CharactersKeys
import com.nirwashh.rickandmortyapp.characters.data.model.CharactersResponse
import com.nirwashh.rickandmortyapp.characters.data.remote.CharactersService
import com.nirwashh.rickandmortyapp.core.data.local.RickAndMortyDatabase
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class CharacterRemoteMediator(
    private val service: CharactersService,
    private val database: RickAndMortyDatabase,
    private val name: String?,
    private val status: String?,
    private val gender: String?,
    private val type: String?,
    private val species: String?
) : RemoteMediator<Int, CharacterData>() {

    private val dao = database.characterDao()
    private val keysDao = database.characterKeysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterData>
    ): MediatorResult {

        val page = when (val pageKeyData = getKeyPageData(loadType, state)) {
            is MediatorResult.Success -> return pageKeyData
            else -> pageKeyData as Int
        }

        try {

            val response: CharactersResponse =
                service.fetchCharacters(
                    name = name,
                    status = status,
                    gender = gender,
                    type = type,
                    species = species,
                    page = page
                )

            val isEndOfList = (response.info.next == null
                    || response.toString().contains("error")
                    || response.results.isEmpty())

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    dao.deleteAllCharacters()
                    keysDao.deleteAllCharactersKeys()
                }
                val prevKey = if (page == 1) null else page - 1
                val nextKey = if (isEndOfList) null else page + 1
                val keys = response.results.map {
                    CharactersKeys(it.id, prevPage = prevKey, nextPage = nextKey)
                }
                keysDao.insertAllCharactersKeys(remoteKeysCharacters = keys)
                dao.insertAllCharacters(characters = response.results)
            }
            return MediatorResult.Success(endOfPaginationReached = isEndOfList)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, CharacterData>
    ): CharactersKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                keysDao.getCharactersKeys(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, CharacterData>
    ): CharactersKeys? {
        return state.pages
            .firstOrNull { it.data.isNotEmpty() }
            ?.data?.firstOrNull()
            ?.let {
                keysDao.getCharactersKeys(id = it.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, CharacterData>
    ): CharactersKeys? {
        return state.pages
            .lastOrNull { it.data.isNotEmpty() }
            ?.data?.lastOrNull()
            ?.let {
                keysDao.getCharactersKeys(id = it.id)
            }
    }

    private suspend fun getKeyPageData(
        loadType: LoadType,
        state: PagingState<Int, CharacterData>
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