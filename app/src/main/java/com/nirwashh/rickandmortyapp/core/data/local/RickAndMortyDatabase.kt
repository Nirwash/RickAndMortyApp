package com.nirwashh.rickandmortyapp.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nirwashh.rickandmortyapp.characters.data.local.dao.CharacterDao
import com.nirwashh.rickandmortyapp.characters.data.local.dao.CharacterKeysDao
import com.nirwashh.rickandmortyapp.characters.data.model.CharacterData
import com.nirwashh.rickandmortyapp.characters.data.model.CharactersKeys
import com.nirwashh.rickandmortyapp.core.utils.converter.Converter
import com.nirwashh.rickandmortyapp.episodes.data.local.dao.EpisodeDao
import com.nirwashh.rickandmortyapp.episodes.data.local.dao.EpisodesKeysDao
import com.nirwashh.rickandmortyapp.episodes.data.model.EpisodeData
import com.nirwashh.rickandmortyapp.episodes.data.model.EpisodeKeys
import com.nirwashh.rickandmortyapp.locations.data.local.dao.LocationDao
import com.nirwashh.rickandmortyapp.locations.data.local.dao.LocationsKeysDao
import com.nirwashh.rickandmortyapp.locations.data.local.entities.LocationDbEntity


@Database(
    version = 1,
    entities = [
        CharacterData::class,
        CharactersKeys::class,
        EpisodeData::class,
        EpisodeKeys::class,
        LocationDbEntity::class
    ]
)
@TypeConverters(Converter::class)
abstract class RickAndMortyDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun characterKeysDao(): CharacterKeysDao
    abstract fun getLocationDao(): LocationDao
    abstract fun getLocationsKeyDao(): LocationsKeysDao
    abstract fun episodeDao(): EpisodeDao
    abstract fun episodesKeyDao(): EpisodesKeysDao
}
