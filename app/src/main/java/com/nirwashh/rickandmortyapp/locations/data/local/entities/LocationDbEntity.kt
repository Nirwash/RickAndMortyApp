package com.nirwashh.rickandmortyapp.locations.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "locations_table")
data class LocationDbEntity(
    val created: String,
    val dimension: String,
    val id: Int,
    val name: String,
    val type: String,
    @PrimaryKey(autoGenerate = false)
    val locationUrl: String
)