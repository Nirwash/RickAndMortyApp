package com.nirwashh.rickandmortyapp.characters.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nirwashh.rickandmortyapp.characters.data.cache.converters.CharacterConverters
import com.nirwashh.rickandmortyapp.characters.data.cache.entities.CharacterDbEntity

@TypeConverters(CharacterConverters::class)
@Database(
    version = 1,
    entities = [
        CharacterDbEntity::class
    ]
)
abstract class CharactersDatabase : RoomDatabase() {
    abstract fun charactersDao(): CharactersDao
}
