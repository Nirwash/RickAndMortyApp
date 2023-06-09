package com.nirwashh.rickandmortyapp.locations.data.remote

import com.nirwashh.rickandmortyapp.locations.data.model.LocationData
import com.nirwashh.rickandmortyapp.locations.data.model.LocationResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LocationService {

    @GET("location")
    suspend fun fetchLocations(
        @Query("page")
        page: Int,
        @Query("name")
        name: String?,
        @Query("type")
        type: String?,
        @Query("dimension")
        dimension: String?
    ): LocationResponse

    @GET("location")
    suspend fun fetchLocationByName(
        @Query("name") name: String
    ): Response<LocationResponse>

    @GET("location/{id}")
    fun fetchObservableLocationById(
        @Path("id") id: Int
    ): Observable<LocationData>
}