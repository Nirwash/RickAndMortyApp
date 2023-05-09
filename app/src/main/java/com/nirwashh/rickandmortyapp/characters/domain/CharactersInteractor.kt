package com.nirwashh.rickandmortyapp.characters.domain

import com.nirwashh.rickandmortyapp.characters.data.model.CharacterFilters

class CharactersInteractor(private val repository: CharactersRepository) {
    suspend fun getCharacters(filters: CharacterFilters) =
        repository.getCharacters(filters)

    suspend fun getCharactersByIds(ids: String) = repository.getCharactersByIds(ids)

    fun getObservableCharactersByIds(ids: String) = repository.getObservableCharactersByIds(ids)


}