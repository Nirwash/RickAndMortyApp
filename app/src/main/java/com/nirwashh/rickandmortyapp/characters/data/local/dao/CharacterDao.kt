package com.nirwashh.rickandmortyapp.characters.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nirwashh.rickandmortyapp.characters.data.model.CharacterData
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: CharacterData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCharacters(characters: List<CharacterData>)

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
    ): Flow<CharacterData>

    @Query(
        "SELECT * FROM characters_table WHERE (:name IS NULL OR name LIKE '%' || :name || '%') " +
                "AND (:status IS NULL OR status LIKE :status) " +
                "AND (:species IS NULL OR species LIKE :species) " +
                "AND (:type IS NULL OR type LIKE :type) " +
                "AND (:gender IS NULL OR gender LIKE :gender)"
    )
    fun getPagedCharacters(
        name: String?,
        status: String?,
        species: String?,
        type: String?,
        gender: String?
    ): PagingSource<Int, CharacterData>

    @Query("SELECT * FROM characters_table WHERE id = :id")
    suspend fun getCharacterById(id: Int): CharacterData

    @Query("DELETE FROM characters_table")
    suspend fun deleteAllCharacters()
}