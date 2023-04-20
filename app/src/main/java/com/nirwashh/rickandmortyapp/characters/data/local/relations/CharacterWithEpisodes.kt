package com.nirwashh.rickandmortyapp.characters.data.local.relations
//
//import androidx.room.Embedded
//import androidx.room.Junction
//import androidx.room.Relation
//import com.nirwashh.rickandmortyapp.characters.data.local.entities.CharacterDbEntity
//import com.nirwashh.rickandmortyapp.episodes.data.local.entities.EpisodeDbEntity
//
//data class CharacterWithEpisodes(
//    @Embedded
//    val character: CharacterDbEntity,
//    @Relation(
//        parentColumn = "character_url",
//        entityColumn = "episode_url",
//        associateBy = Junction(CharacterEpisodeCrossRef::class)
//    )
//    val episodes: List<EpisodeDbEntity>
//)
