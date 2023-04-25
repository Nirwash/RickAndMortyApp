package com.nirwashh.rickandmortyapp.episodes.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

//import com.nirwashh.rickandmortyapp.episodes.data.local.entities.EpisodeDbEntity

@Parcelize
data class Episode(
    val air_date: String,
    val characters: List<String>,
    val created: String,
    val episode: String,
    val id: Int,
    val name: String,
    val url: String
) : Parcelable {
//    companion object {
//        fun Episode.toEpisodeDbEntity() =
//            EpisodeDbEntity(
//                air_date = air_date,
//                created = created,
//                episode = episode,
//                id = id,
//                name = name,
//                url = url
//            )
//    }

}