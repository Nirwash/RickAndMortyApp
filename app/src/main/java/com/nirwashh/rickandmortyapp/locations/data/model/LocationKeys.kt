package com.nirwashh.rickandmortyapp.locations.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "location_keys")
data class LocationKeys(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?
)