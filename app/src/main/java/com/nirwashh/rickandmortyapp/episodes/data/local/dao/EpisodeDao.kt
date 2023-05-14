package com.nirwashh.rickandmortyapp.episodes.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nirwashh.rickandmortyapp.episodes.data.model.EpisodeData
import kotlinx.coroutines.flow.Flow

@Dao
interface EpisodeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllEpisodes(episodes: List<EpisodeData>?)

    @Query("DELETE FROM episodes_table")
    suspend fun deleteAllEpisodes()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEpisode(episode: EpisodeData)

    @Query(
        "SELECT * FROM episodes_table" +
                " WHERE (:name IS NULL OR name LIKE '%' || :name || '%')" +
                " AND (:episode IS NULL OR episode LIKE :episode)"
    )
    fun getPagedEpisodes(
        name: String?,
        episode: String?,
    ): PagingSource<Int, EpisodeData>

    @Query(
        "SELECT * FROM episodes_table WHERE id IN (:ids) ORDER BY id ASC"
    )
    suspend fun getEpisodesByIds(ids: List<Int>): List<EpisodeData>

    @Query("SELECT * FROM episodes_table WHERE id = :id")
    suspend fun getEpisodeById(id: Int): EpisodeData

    @Query("SELECT * FROM episodes_table WHERE id = :id")
    fun getObservableEpisodeById(id: Int): EpisodeData

    @Query(
        "SELECT episode FROM episodes_table ORDER BY episode ASC"
    )
    fun getEpisodes(): Flow<List<String>>
}