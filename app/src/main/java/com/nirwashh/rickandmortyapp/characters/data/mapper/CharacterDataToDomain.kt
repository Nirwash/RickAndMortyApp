package com.nirwashh.rickandmortyapp.characters.data.mapper

import com.nirwashh.rickandmortyapp.characters.data.model.CharacterData
import com.nirwashh.rickandmortyapp.characters.domain.model.CharacterDomain
import com.nirwashh.rickandmortyapp.core.utils.Mapper
import javax.inject.Inject

class CharacterDataToDomain @Inject constructor() : Mapper<CharacterDomain, CharacterData> {
    override fun map(source: CharacterData) = CharacterDomain(
        episode = source.episode,
        gender = source.gender,
        id = source.id,
        image = source.image,
        location = mapOf(
            Pair("locationName", source.location.name),
            Pair("locationId", source.location.url.substringAfterLast("/"))
        ),
        name = source.name,
        origin = mapOf(
            Pair("locationName", source.origin.name),
            Pair("locationId", source.origin.url.substringAfterLast("/"))
        ),
        species = source.species,
        status = source.status,
        type = source.type
    )
}