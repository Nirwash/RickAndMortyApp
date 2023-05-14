package com.nirwashh.rickandmortyapp.characters.di

import androidx.paging.ExperimentalPagingApi
import com.nirwashh.rickandmortyapp.characters.data.mapper.CharacterDataToDomain
import com.nirwashh.rickandmortyapp.characters.data.remote.CharactersService
import com.nirwashh.rickandmortyapp.characters.data.repository.CharactersRepositoryImpl
import com.nirwashh.rickandmortyapp.characters.domain.CharactersRepository
import com.nirwashh.rickandmortyapp.core.data.local.RickAndMortyDatabase
import dagger.Module
import dagger.Provides

@Module
class CharacterDataModule {

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    fun provideCharactersRepository(
        service: CharactersService,
        database: RickAndMortyDatabase,
        characterDataToDomain: CharacterDataToDomain
    ): CharactersRepository =
        CharactersRepositoryImpl(service, database, characterDataToDomain)
}