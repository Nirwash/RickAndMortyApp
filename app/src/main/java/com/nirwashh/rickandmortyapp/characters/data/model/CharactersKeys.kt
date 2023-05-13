package com.nirwashh.rickandmortyapp.characters.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters_page_keys")
data class CharactersKeys(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?
)
