package com.nirwashh.rickandmortyapp.episodes.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "episodes_table")
data class EpisodeData(
    val air_date: String,
    val characters: List<String>,
    val created: String,
    val episode: String,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val url: String
)