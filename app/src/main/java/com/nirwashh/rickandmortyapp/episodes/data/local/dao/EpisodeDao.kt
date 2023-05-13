package com.nirwashh.rickandmortyapp.episodes.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.nirwashh.rickandmortyapp.episodes.data.local.entities.EpisodeDbEntity

@Dao
interface EpisodeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEpisodes(episodeDbEntity: EpisodeDbEntity)
}