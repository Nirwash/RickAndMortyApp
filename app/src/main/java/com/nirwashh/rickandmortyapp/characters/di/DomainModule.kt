package com.nirwashh.rickandmortyapp.characters.di

import com.nirwashh.rickandmortyapp.characters.domain.CharactersInteractor
import com.nirwashh.rickandmortyapp.characters.domain.CharactersRepository
import dagger.Module
import dagger.Provides


@Module
class DomainModule {

    @Provides
    fun provideCharacterInteractor(charactersRepository: CharactersRepository): CharactersInteractor {
        return CharactersInteractor(charactersRepository)
    }
}