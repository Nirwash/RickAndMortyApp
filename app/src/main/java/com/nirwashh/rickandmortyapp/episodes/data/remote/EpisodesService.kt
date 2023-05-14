package com.nirwashh.rickandmortyapp.episodes.data.remote

import com.nirwashh.rickandmortyapp.episodes.data.model.EpisodeData
import com.nirwashh.rickandmortyapp.episodes.data.model.EpisodesResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EpisodesService {

    @GET("episode/{ids}")
    suspend fun fetchMultipleEpisodes(
        @Path("ids") ids: String
    ): List<EpisodeData>

    @GET("episode/")
    suspend fun fetchEpisodes(
        @Query("page")
        page: Int,
        @Query("name")
        name: String?,
        @Query("episode")
        episode: String?

    ): EpisodesResponse

    @GET("episode/{ids}")
    fun fetchObservableMultipleEpisodes(
        @Path("ids") ids: String
    ): Observable<List<EpisodeData>>
}