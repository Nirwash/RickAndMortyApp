package com.nirwashh.rickandmortyapp.characters.data.local
//
//import androidx.room.Database
//import androidx.room.RoomDatabase
//import com.nirwashh.rickandmortyapp.characters.data.local.entities.CharacterDbEntity
//import com.nirwashh.rickandmortyapp.characters.data.local.relations.CharacterEpisodeCrossRef
//import com.nirwashh.rickandmortyapp.episodes.data.local.entities.EpisodeDbEntity
//import com.nirwashh.rickandmortyapp.locations.data.local.entities.LocationDbEntity
//
//
//@Database(
//    version = 1,
//    entities = [
//        CharacterDbEntity::class,
//        EpisodeDbEntity::class,
//        LocationDbEntity::class,
//        CharacterEpisodeCrossRef::class
//    ]
//)
//abstract class CharactersDatabase : RoomDatabase() {
//    abstract fun charactersDao(): CharactersDao
//}
