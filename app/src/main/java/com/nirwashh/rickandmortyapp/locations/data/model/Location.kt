package com.nirwashh.rickandmortyapp.locations.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

//import com.nirwashh.rickandmortyapp.locations.data.local.entities.LocationDbEntity

@Parcelize
data class Location(
    val created: String,
    val dimension: String,
    val id: Int,
    val name: String,
    val residents: List<String>,
    val type: String,
    val url: String
) : Parcelable {
//    companion object {
//        fun Location.toLocationDbEntity() =
//            LocationDbEntity(
//                created = created,
//                dimension = dimension,
//                id = id,
//                name = name,
//                type = type,
//                url = url
//            )
//    }

}