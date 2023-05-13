package com.nirwashh.rickandmortyapp.characters.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.nirwashh.rickandmortyapp.characters.data.mapper.CharacterDataToDomain
import com.nirwashh.rickandmortyapp.characters.data.paging.CharacterRemoteMediator
import com.nirwashh.rickandmortyapp.characters.data.remote.CharactersService
import com.nirwashh.rickandmortyapp.characters.domain.CharactersRepository
import com.nirwashh.rickandmortyapp.characters.domain.model.CharacterDomain
import com.nirwashh.rickandmortyapp.core.data.local.RickAndMortyDatabase
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@ExperimentalPagingApi
class CharactersRepositoryImpl(
    private val characterService: CharactersService,
    private val characterDatabase: RickAndMortyDatabase,
    private val characterDataToDomain: CharacterDataToDomain
) : CharactersRepository {

    override fun getCharacters(
        name: String?,
        status: String?,
        gender: String?,
        type: String?,
        species: String?
    ): Flow<PagingData<CharacterDomain>> {

        val pagingSourceFactory = {
            characterDatabase.characterDao().getPagedCharacters(
                name = name,
                status = status,
                species = species,
                type = type,
                gender = gender
            )

        }

        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 2,
                maxSize = PagingConfig.MAX_SIZE_UNBOUNDED,
                jumpThreshold = Int.MIN_VALUE
            ),
            remoteMediator = CharacterRemoteMediator(
                service = characterService,
                database = characterDatabase,
                name = name, status = status, gender = gender, type = type, species = species
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow.map { pagingData ->
            pagingData.map {
                characterDataToDomain.map(it)
            }
        }

    }

    override suspend fun getCharactersByIds(ids: String) =
        characterService.fetchMultipleCharacters(ids).map { characterDataToDomain.map(it) }

    override fun getObservableCharactersByIds(ids: String): Single<List<CharacterDomain>> {
        val response = characterService.fetchObservableMultipleCharacters(ids)
        return response.map { list ->
            list.map { characterDataToDomain.map(it) }
        }
    }

}