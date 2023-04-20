package com.nirwashh.rickandmortyapp.characters.data.model

import com.nirwashh.rickandmortyapp.characters.data.cache.entities.CharacterDbEntity
import com.nirwashh.rickandmortyapp.locations.data.model.Location
import com.nirwashh.rickandmortyapp.locations.data.model.Origin

data class Character(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String
) {
    fun toCharacterDbEntity(): CharacterDbEntity {
        return CharacterDbEntity(
            created = created,
            //todo mapping episodesId = episode.forEach { it.id },
            gender = gender,
            characterId = id,
            image = image,
            locationId = location.id,
            name = name,
            //originId = origin.name,
            species = species,
            status = status,
            type = type,
            url = url
        )
    }
}