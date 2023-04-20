package com.nirwashh.rickandmortyapp.characters.domain

import com.nirwashh.rickandmortyapp.characters.data.model.Character
import com.nirwashh.rickandmortyapp.characters.data.model.CharacterFilters
import com.nirwashh.rickandmortyapp.characters.data.model.CharactersResponse
import retrofit2.Response

interface CharactersRepository {
    suspend fun aboutCharacter(character: Character)
    suspend fun getCharacters(filters: CharacterFilters): Response<CharactersResponse>
}