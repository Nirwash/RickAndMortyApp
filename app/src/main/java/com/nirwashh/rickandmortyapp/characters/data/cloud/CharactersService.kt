package com.nirwashh.rickandmortyapp.characters.data.cloud

import com.nirwashh.rickandmortyapp.characters.data.model.CharactersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CharactersService {

    @GET("character")
    suspend fun fetchCharacters(
        @Query("name")
        name: String,
        @Query("status")
        status: String,
        @Query("species")
        species: String,
        @Query("type")
        type: String,
        @Query("gender")
        gender: String,
        @Query("page")
        page: Int
    ): Response<CharactersResponse>
}