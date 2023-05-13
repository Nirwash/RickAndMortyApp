package com.nirwashh.rickandmortyapp.locations.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.nirwashh.rickandmortyapp.locations.data.local.entities.LocationDbEntity

@Dao
interface LocationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocations(locationDbEntity: LocationDbEntity)
}