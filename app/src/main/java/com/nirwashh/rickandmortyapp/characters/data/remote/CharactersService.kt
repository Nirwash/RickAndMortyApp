package com.nirwashh.rickandmortyapp.characters.data.remote

import com.nirwashh.rickandmortyapp.characters.data.model.Character
import com.nirwashh.rickandmortyapp.characters.data.model.CharactersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
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

    @GET("character/{ids}")
    suspend fun fetchMultipleCharacters(
        @Path("ids") ids: String
    ): List<Character>
}