package com.nirwashh.rickandmortyapp.characters.di

import com.nirwashh.rickandmortyapp.characters.data.remote.CharactersService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class CharacterApiModule {

    @Singleton
    @Provides
    fun provideCharacterApi(retrofit: Retrofit): CharactersService =
        retrofit.create(CharactersService::class.java)

}
