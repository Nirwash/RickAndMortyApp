package com.nirwashh.rickandmortyapp.episodes.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "episodes_table")
data class EpisodeDbEntity(
    val air_date: String,
    val created: String,
    val episode: String,
    val id: Int,
    val name: String,
    @PrimaryKey(autoGenerate = false)
    val episodeUrl: String
)