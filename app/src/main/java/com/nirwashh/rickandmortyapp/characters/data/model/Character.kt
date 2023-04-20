package com.nirwashh.rickandmortyapp.characters.data.model

//import com.nirwashh.rickandmortyapp.characters.data.local.entities.CharacterDbEntity
import com.nirwashh.rickandmortyapp.locations.data.model.Location

data class Character(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Location,
    val species: String,
    val status: String,
    val type: String,
    val url: String
) {
//    companion object {
//        fun Character.toCharacterBdEntity() =
//            CharacterDbEntity(
//                created = created,
//                gender = gender,
//                id = id,
//                image = image,
//                name = name,
//                species = species,
//                status = status,
//                type = type,
//                url = url
//            )
//    }

}