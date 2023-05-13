package com.nirwashh.rickandmortyapp.characters.data.remote


import com.nirwashh.rickandmortyapp.characters.data.model.CharacterData
import com.nirwashh.rickandmortyapp.characters.data.model.CharactersResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharactersService {

    @GET("character/")
    suspend fun fetchCharacters(
        @Query("page")
        page: Int,
        @Query("name")
        name: String?,
        @Query("status")
        status: String?,
        @Query("species")
        species: String?,
        @Query("type")
        type: String?,
        @Query("gender")
        gender: String?
    ): CharactersResponse

    @GET("character/{ids}")
    suspend fun fetchMultipleCharacters(
        @Path("ids") ids: String
    ): List<CharacterData>

    @GET("character/{ids}")
    fun fetchObservableMultipleCharacters(
        @Path("ids") ids: String
    ): Single<List<CharacterData>>
}