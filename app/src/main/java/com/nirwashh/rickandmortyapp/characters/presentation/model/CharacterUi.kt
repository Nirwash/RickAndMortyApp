package com.nirwashh.rickandmortyapp.characters.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterUi(
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: Map<String, String>,
    val name: String,
    val origin: Map<String, String>,
    val species: String,
    val status: String,
    val type: String
) : Parcelable