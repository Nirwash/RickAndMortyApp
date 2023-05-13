package com.nirwashh.rickandmortyapp.characters.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters_table")
data class CharacterData(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val image: String,
    @ColumnInfo(name = "origin")
    val origin: Location,
    @ColumnInfo(name = "location")
    val location: Location,
    @ColumnInfo(name = "episodes")
    val episode: List<String>
)