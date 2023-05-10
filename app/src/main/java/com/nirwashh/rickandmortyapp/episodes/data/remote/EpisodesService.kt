package com.nirwashh.rickandmortyapp.episodes.data.remote

import com.nirwashh.rickandmortyapp.episodes.data.model.Episode
import com.nirwashh.rickandmortyapp.episodes.data.model.EpisodesResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EpisodesService {

    @GET("episode/{ids}")
    suspend fun fetchMultipleEpisodes(
        @Path("ids") ids: String
    ): List<Episode>

    @GET("episode")
    suspend fun fetchEpisodes(
        @Query("name")
        name: String,
        @Query("episode")
        episode: String,
        @Query("page")
        page: Int
    ): Response<EpisodesResponse>

    @GET("episode/{ids}")
    fun fetchObservableMultipleEpisodes(
        @Path("ids") ids: String
    ): Single<List<Episode>>
}