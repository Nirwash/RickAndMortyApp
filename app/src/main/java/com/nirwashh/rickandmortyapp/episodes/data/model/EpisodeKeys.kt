package com.nirwashh.rickandmortyapp.episodes.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "episode_page_keys")
data class EpisodeKeys(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?
)