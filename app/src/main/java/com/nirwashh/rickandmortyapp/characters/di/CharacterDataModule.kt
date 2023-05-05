package com.nirwashh.rickandmortyapp.characters.di

import com.nirwashh.rickandmortyapp.characters.data.remote.CharactersService
import com.nirwashh.rickandmortyapp.characters.data.repository.CharactersRepositoryImpl
import com.nirwashh.rickandmortyapp.characters.domain.CharactersRepository
import com.nirwashh.rickandmortyapp.core.data.local.RickAndMortyDao
import dagger.Module
import dagger.Provides

@Module
class CharacterDataModule {

    @Provides
    fun provideCharactersRepository(
        service: CharactersService,
        dao: RickAndMortyDao
    ): CharactersRepository = CharactersRepositoryImpl(service, dao)
}