package com.nirwashh.rickandmortyapp.characters.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters_table")
data class CharacterDbEntity(
    val created: String,
    val gender: String,
    val id: Int,
    val image: String,
    val name: String,
    val species: String,
    val status: String,
    val type: String,
    @PrimaryKey(autoGenerate = false)
    val characterUrl: String,
    val locationUrl: String,
    val originUrl: String
)