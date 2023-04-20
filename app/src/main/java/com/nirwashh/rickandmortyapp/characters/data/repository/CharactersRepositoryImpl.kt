package com.nirwashh.rickandmortyapp.characters.data.repository

import com.nirwashh.rickandmortyapp.characters.data.cache.CharactersDao
import com.nirwashh.rickandmortyapp.characters.data.cloud.CharactersService
import com.nirwashh.rickandmortyapp.characters.data.model.Character
import com.nirwashh.rickandmortyapp.characters.data.model.CharacterFilters
import com.nirwashh.rickandmortyapp.characters.data.model.CharactersResponse
import com.nirwashh.rickandmortyapp.characters.domain.CharactersRepository
import retrofit2.Response

class CharactersRepositoryImpl(
    private val characterService: CharactersService,
    private val charactersDao: CharactersDao
) : CharactersRepository {

    override suspend fun aboutCharacter(character: Character) {
        TODO("Not yet implemented")
    }

    override suspend fun getCharacters(filters: CharacterFilters): Response<CharactersResponse> {
        val response = characterService.fetchCharacters(
            name = filters.name,
            status = filters.status,
            species = filters.species,
            type = filters.type,
            gender = filters.gender,
            page = filters.page
        )
        val characters = response.body()?.results ?: emptyList()
        characters.forEach {
            charactersDao.insertCharacters(it.toCharacterDbEntity())
        }

        return characterService.fetchCharacters(
            name = filters.name,
            status = filters.status,
            species = filters.species,
            type = filters.type,
            gender = filters.gender,
            page = filters.page
        )
    }
}