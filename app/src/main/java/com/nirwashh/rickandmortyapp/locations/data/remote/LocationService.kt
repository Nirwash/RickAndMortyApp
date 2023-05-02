package com.nirwashh.rickandmortyapp.locations.data.remote

import com.nirwashh.rickandmortyapp.locations.data.model.LocationResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationService {

    @GET("location")
    suspend fun fetchLocations(
        @Query("name")
        name: String,
        @Query("type")
        type: String,
        @Query("dimension")
        dimension: String,
        @Query("page")
        page: Int
    ): Response<LocationResponse>
}