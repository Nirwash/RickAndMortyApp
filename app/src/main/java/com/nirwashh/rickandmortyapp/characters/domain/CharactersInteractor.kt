package com.nirwashh.rickandmortyapp.characters.domain

import com.nirwashh.rickandmortyapp.characters.data.model.CharacterFilters
import javax.inject.Inject

class CharactersInteractor @Inject constructor(private val repository: CharactersRepository) {
    suspend fun getCharacters(filters: CharacterFilters) =
        repository.getCharacters(filters)
}