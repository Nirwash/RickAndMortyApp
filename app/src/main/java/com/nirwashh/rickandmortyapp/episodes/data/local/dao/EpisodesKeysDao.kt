package com.nirwashh.rickandmortyapp.episodes.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nirwashh.rickandmortyapp.episodes.data.model.EpisodeKeys

@Dao
interface EpisodesKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllEpisodesKeys(remoteKeysEpisodes: List<EpisodeKeys>?)

    @Query("SELECT * FROM episode_page_keys WHERE id =:id")
    suspend fun getEpisodesRemoteKeys(id: Int): EpisodeKeys

    @Query("DELETE FROM episode_page_keys")
    suspend fun deleteAllEpisodesRemoteKeys()
}