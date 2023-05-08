package com.nirwashh.rickandmortyapp.characters.domain

import androidx.paging.PagingData
import com.nirwashh.rickandmortyapp.characters.data.model.Character
import com.nirwashh.rickandmortyapp.characters.data.model.CharacterFilters
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    suspend fun getCharacters(filters: CharacterFilters): Flow<PagingData<Character>>
    suspend fun getCharactersByIds(ids: String): List<Character>
    fun getObservableCharactersByIds(ids: String): Single<List<Character>>
}