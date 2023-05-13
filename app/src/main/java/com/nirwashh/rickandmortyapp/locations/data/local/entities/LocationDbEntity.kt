package com.nirwashh.rickandmortyapp.locations.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "locations_table")
data class LocationDbEntity(
    val created: String,
    val dimension: String,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val type: String,
    val locationUrl: String
)