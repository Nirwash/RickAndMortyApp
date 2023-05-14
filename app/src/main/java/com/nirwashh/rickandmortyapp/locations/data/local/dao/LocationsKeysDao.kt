package com.nirwashh.rickandmortyapp.locations.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nirwashh.rickandmortyapp.locations.data.model.LocationKeys

@Dao
interface LocationsKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertKeys(remoteKeysLocations: List<LocationKeys>)

    @Query("SELECT * FROM location_keys WHERE id =:id")
    suspend fun getKeys(id: Int): LocationKeys

    @Query("DELETE FROM location_keys")
    suspend fun deleteKeys()
}