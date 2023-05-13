package com.nirwashh.rickandmortyapp.characters.domain

class CharactersInteractor(private val repository: CharactersRepository) {
    fun getCharacters(
        name: String?,
        status: String?,
        gender: String?,
        type: String?,
        species: String?
    ) =
        repository.getCharacters(
            name = name,
            status = status,
            gender = gender,
            type = type,
            species = species
        )

    suspend fun getCharactersByIds(ids: String) = repository.getCharactersByIds(ids)

    fun getObservableCharactersByIds(ids: String) = repository.getObservableCharactersByIds(ids)


}