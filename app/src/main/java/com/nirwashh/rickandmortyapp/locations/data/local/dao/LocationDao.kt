package com.nirwashh.rickandmortyapp.locations.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nirwashh.rickandmortyapp.locations.data.model.LocationData

@Dao
interface LocationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLocations(locations: List<LocationData?>?)

    @Query("DELETE FROM LOCATIONS_TABLE")
    suspend fun deleteLocations()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocation(location: LocationData)


    @Query(
        "SELECT * FROM LOCATIONS_TABLE WHERE (:name IS NULL OR name LIKE '%' || :name || '%') " +
                "AND (:type IS NULL OR type LIKE :type) " +
                "AND (:dimension IS NULL OR dimension LIKE :dimension)"
    )
    fun getPagedLocations(
        name: String?,
        type: String?,
        dimension: String?,
    ): PagingSource<Int, LocationData>


    @Query("SELECT * FROM LOCATIONS_TABLE WHERE id = :id")
    suspend fun getLocationById(id: Int): LocationData

}