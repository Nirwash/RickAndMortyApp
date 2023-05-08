package com.nirwashh.rickandmortyapp.characters.data.local.relations

import androidx.room.Entity

@Entity(primaryKeys = ["characterUrl", "episodeUrl"])
data class CharacterEpisodeCrossRef(
    val characterUrl: String,
    val episodeUrl: String
)
