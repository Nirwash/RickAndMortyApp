package com.nirwashh.rickandmortyapp.locations.data.cache.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "locations_table")
data class LocationDbEntity(
    val created: String,
    val dimension: String,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    @ColumnInfo(name = "residents_id")
    val residentsId: List<Int>,
    val type: String,
    val url: String
)