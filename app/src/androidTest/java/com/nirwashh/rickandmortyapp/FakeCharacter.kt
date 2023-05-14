package com.nirwashh.rickandmortyapp

import com.nirwashh.rickandmortyapp.characters.presentation.model.CharacterUi
import com.nirwashh.rickandmortyapp.core.utils.Mapper

data class FakeCharacter(
    val episode: List<String> = listOf(
        "episode 1",
        "episode 2",
        "episode 3"
    ),
    val gender: String = "male",
    val id: Int = 666,
    val image: String = "image.jpg",
    val location: Map<String, String> = mapOf(
        Pair("firstKey", "firstValue"),
        Pair("secondKey", "secondValue")
    ),
    val name: String = "Mozgotron 3000",
    val origin: Map<String, String> = mapOf(
        Pair("firstKey", "firstValue"),
        Pair("secondKey", "secondValue")
    ),
    val species: String = "kiborg-ubitsa",
    val status: String = "immortal",
    val type: String = "bipolar"
)

class CharacterFakeToUiMapper : Mapper<CharacterUi, FakeCharacter> {
    override fun map(source: FakeCharacter) = CharacterUi(
        episode = source.episode,
        gender = source.gender,
        id = source.id,
        image = source.image,
        location = source.location,
        name = source.name,
        origin = source.origin,
        species = source.species,
        status = source.status,
        type = source.type
    )

}