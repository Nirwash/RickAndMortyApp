package com.nirwashh.rickandmortyapp.locations.data.model

//import com.nirwashh.rickandmortyapp.locations.data.local.entities.LocationDbEntity

data class Location(
    val created: String,
    val dimension: String,
    val id: Int,
    val name: String,
    val residents: List<String>,
    val type: String,
    val url: String
) {
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