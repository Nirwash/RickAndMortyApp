package com.nirwashh.rickandmortyapp.episodes.data.cache.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "episodes_table")
data class EpisodeDbEntity(
    val air_date: String,
    @ColumnInfo(name = "characters_id")
    val charactersId: List<Int>,
    val created: String,
    val episode: String,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val url: String
)