package com.nirwashh.rickandmortyapp.characters.domain

import androidx.paging.PagingData
import com.nirwashh.rickandmortyapp.characters.domain.model.CharacterDomain
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    fun getCharacters(
        name: String?,
        status: String?,
        gender: String?,
        type: String?,
        species: String?
    ): Flow<PagingData<CharacterDomain>>

    suspend fun getCharactersByIds(ids: String): List<CharacterDomain>
    fun getObservableCharactersByIds(ids: String): Single<List<CharacterDomain>>
}