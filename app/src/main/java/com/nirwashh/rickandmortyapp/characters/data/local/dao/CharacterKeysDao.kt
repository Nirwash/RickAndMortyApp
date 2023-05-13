package com.nirwashh.rickandmortyapp.characters.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nirwashh.rickandmortyapp.characters.data.model.CharactersKeys

@Dao
interface CharacterKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCharactersKeys(remoteKeysCharacters: List<CharactersKeys>)

    @Query("SELECT * FROM characters_page_keys WHERE id =:id")
    suspend fun getCharactersKeys(id: Int): CharactersKeys

    @Query("DELETE FROM characters_page_keys")
    suspend fun deleteAllCharactersKeys()
}