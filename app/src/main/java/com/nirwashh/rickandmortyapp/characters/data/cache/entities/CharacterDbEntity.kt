package com.nirwashh.rickandmortyapp.characters.data.cache.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.nirwashh.rickandmortyapp.characters.data.cache.converters.CharacterConverters

@Entity(
    "characters_table",
    indices = [
        Index("name"),
        Index("character_id")
    ]
)
@TypeConverters(CharacterConverters::class)
data class CharacterDbEntity(
    val created: String,
    //todo mapping @ColumnInfo(name = "episodes_id")
    //val episodesId: List<Int>,
    val gender: String,
    @PrimaryKey
    @ColumnInfo(name = "character_id")
    val characterId: Int,
    val image: String,
    @ColumnInfo(name = "location_id")
    val locationId: Int,
    val name: String,
    // @ColumnInfo(name = "origin_id")
    //val originId: Int,
    val species: String,
    val status: String,
    val type: String,
    val url: String
) {
}