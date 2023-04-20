package com.nirwashh.rickandmortyapp.characters.data.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nirwashh.rickandmortyapp.characters.data.cache.entities.CharacterDbEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharactersDao {

    @Query(
        "SELECT * FROM characters_table WHERE name = :name " +
                "AND status = :status AND species = :species " +
                "AND type = :type AND gender = :gender"
    )
    fun getCharacters(
        name: String,
        status: String,
        species: String,
        type: String,
        gender: String
    ): Flow<CharacterDbEntity>

    @Insert(onConflict = OnConflictStrategy.NONE)
    suspend fun insertCharacters(character: CharacterDbEntity)

    @Query("SELECT * FROM characters_table WHERE character_id = :characterId")
    suspend fun getCharacterById(characterId: Int): CharacterDbEntity
}