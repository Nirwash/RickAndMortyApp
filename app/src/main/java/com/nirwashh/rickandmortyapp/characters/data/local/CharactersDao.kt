package com.nirwashh.rickandmortyapp.characters.data.local
//
//import androidx.room.Dao
//import androidx.room.Insert
//import androidx.room.OnConflictStrategy
//import androidx.room.Query
//import androidx.room.Transaction
//import com.nirwashh.rickandmortyapp.characters.data.local.entities.CharacterDbEntity
//import com.nirwashh.rickandmortyapp.characters.data.local.relations.CharacterEpisodeCrossRef
//import com.nirwashh.rickandmortyapp.characters.data.local.relations.CharacterWithEpisodes
//import com.nirwashh.rickandmortyapp.episodes.data.local.entities.EpisodeDbEntity
//import com.nirwashh.rickandmortyapp.episodes.data.local.entities.relations.EpisodeWithCharacters
//import com.nirwashh.rickandmortyapp.locations.data.local.entities.LocationDbEntity
//import com.nirwashh.rickandmortyapp.locations.data.local.relations.LocationWithCharacters
//import com.nirwashh.rickandmortyapp.locations.data.local.relations.OriginWithCharacters
//import kotlinx.coroutines.flow.Flow
//
//@Dao
//interface CharactersDao {
//
//    @Query(
//        "SELECT * FROM characters_table WHERE name = :name " +
//                "AND status = :status AND species = :species " +
//                "AND type = :type AND gender = :gender"
//    )
//    fun getCharacters(
//        name: String,
//        status: String,
//        species: String,
//        type: String,
//        gender: String
//    ): Flow<CharacterDbEntity>
//
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertCharacters(character: CharacterDbEntity)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertEpisodes(episodeDbEntity: EpisodeDbEntity)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertLocations(locationDbEntity: LocationDbEntity)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertCharacterEpisodeCrossRef(crossRef: CharacterEpisodeCrossRef)
//
//    @Query("SELECT * FROM characters_table WHERE character_url = :characterUrl")
//    suspend fun getCharacterById(characterUrl: String): CharacterDbEntity
//
//    @Transaction
//    @Query("SELECT * FROM characters_table WHERE character_url = :characterUrl")
//    suspend fun getCharacterAndLocation(characterUrl: String): List<LocationWithCharacters>
//
//    @Transaction
//    @Query("SELECT * FROM characters_table WHERE character_url = :characterUrl")
//    suspend fun getCharacterAndOrigin(characterUrl: String): List<OriginWithCharacters>
//
//    @Transaction
//    @Query("SELECT * FROM episodes_table WHERE episode_url = :episodeUrl")
//    suspend fun getCharactersOfEpisode(episodeUrl: String): List<CharacterWithEpisodes>
//
//    @Transaction
//    @Query("SELECT * FROM characters_table WHERE character_url = :characterUrl")
//    suspend fun getEpisodesOfCharacter(characterUrl: String): List<EpisodeWithCharacters>
//}