package com.nirwashh.rickandmortyapp.locations.data.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.nirwashh.rickandmortyapp.characters.data.local.entities.CharacterDbEntity
import com.nirwashh.rickandmortyapp.locations.data.local.entities.LocationDbEntity

data class LocationWithCharacters(
    @Embedded
    val location: LocationDbEntity,
    @Relation(
        parentColumn = "locationUrl",
        entityColumn = "locationUrl"
    )
    val characters: List<CharacterDbEntity>
)
