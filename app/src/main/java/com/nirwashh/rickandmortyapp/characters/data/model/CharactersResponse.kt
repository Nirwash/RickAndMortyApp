package com.nirwashh.rickandmortyapp.characters.data.model

data class CharactersResponse(
    val info: Info,
    val results: List<Character>
)