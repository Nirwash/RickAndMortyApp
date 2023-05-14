package com.nirwashh.rickandmortyapp.locations.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "locations_table")
data class LocationData(
    val created: String,
    val dimension: String,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val residents: List<String>,
    val type: String,
    val url: String
)