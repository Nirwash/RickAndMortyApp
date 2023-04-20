package com.nirwashh.rickandmortyapp.characters.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.nirwashh.rickandmortyapp.characters.data.CharactersPagingSource
import com.nirwashh.rickandmortyapp.characters.data.model.Character
import com.nirwashh.rickandmortyapp.characters.data.model.CharacterFilters
import com.nirwashh.rickandmortyapp.characters.data.remote.CharactersService
import com.nirwashh.rickandmortyapp.characters.domain.CharactersRepository
import kotlinx.coroutines.flow.Flow

class CharactersRepositoryImpl(
    private val characterService: CharactersService,
    /*private val charactersDao: CharactersDao*/
) : CharactersRepository {

    override suspend fun aboutCharacter(character: Character) {
        TODO("Not yet implemented")
    }

    override suspend fun getCharacters(
        filters: CharacterFilters
    ): Flow<PagingData<Character>> = Pager(
        config = PagingConfig(1),
        pagingSourceFactory = { CharactersPagingSource(characterService, filters)}
    ).flow
}