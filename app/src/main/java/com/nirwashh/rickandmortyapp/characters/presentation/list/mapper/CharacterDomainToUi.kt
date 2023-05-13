package com.nirwashh.rickandmortyapp.characters.presentation.list.mapper

import com.nirwashh.rickandmortyapp.characters.domain.model.CharacterDomain
import com.nirwashh.rickandmortyapp.characters.presentation.list.model.CharacterUi
import com.nirwashh.rickandmortyapp.core.utils.Mapper
import javax.inject.Inject

class CharacterDomainToUi @Inject constructor() : Mapper<CharacterUi, CharacterDomain> {
    override fun map(source: CharacterDomain) = CharacterUi(
        episode = source.episode,
        gender = source.gender,
        id = source.id,
        image = source.image,
        location = source.location,
        name = source.name,
        origin = source.origin,
        species = source.species,
        status = source.status,
        type = source.type
    )
}