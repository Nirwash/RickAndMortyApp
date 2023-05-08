package com.nirwashh.rickandmortyapp.episodes.data.local.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.nirwashh.rickandmortyapp.characters.data.local.entities.CharacterDbEntity
import com.nirwashh.rickandmortyapp.characters.data.local.relations.CharacterEpisodeCrossRef
import com.nirwashh.rickandmortyapp.episodes.data.local.entities.EpisodeDbEntity

data class EpisodeWithCharacters(
    @Embedded
    val episodeDbEntity: EpisodeDbEntity,
    @Relation(
        parentColumn = "episodeUrl",
        entityColumn = "characterUrl",
        associateBy = Junction(CharacterEpisodeCrossRef::class)
    )
    val characters: List<CharacterDbEntity>
)
