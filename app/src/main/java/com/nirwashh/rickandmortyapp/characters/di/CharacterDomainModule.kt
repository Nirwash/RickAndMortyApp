package com.nirwashh.rickandmortyapp.characters.di

import com.nirwashh.rickandmortyapp.characters.domain.CharactersInteractor
import com.nirwashh.rickandmortyapp.characters.domain.CharactersRepository
import dagger.Module
import dagger.Provides


@Module
class CharacterDomainModule {

    @Provides
    fun provideCharacterInteractor(charactersRepository: CharactersRepository) =
        CharactersInteractor(charactersRepository)

}