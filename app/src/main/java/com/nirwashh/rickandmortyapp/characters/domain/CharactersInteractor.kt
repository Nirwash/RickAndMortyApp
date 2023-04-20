package com.nirwashh.rickandmortyapp.characters.domain

import com.nirwashh.rickandmortyapp.characters.data.model.Character
import com.nirwashh.rickandmortyapp.characters.data.model.CharacterFilters
import javax.inject.Inject

class CharactersInteractor @Inject constructor(private val repository: CharactersRepository) {

    suspend fun getCharacter(character: Character) = repository.aboutCharacter(character)

    suspend fun getCharacters(filters: CharacterFilters) =
        repository.getCharacters(filters)
}