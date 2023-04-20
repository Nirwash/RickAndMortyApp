package com.nirwashh.rickandmortyapp.characters.di

//import com.nirwashh.rickandmortyapp.characters.data.local.CharactersDao
import com.nirwashh.rickandmortyapp.characters.data.remote.CharactersService
import com.nirwashh.rickandmortyapp.characters.data.repository.CharactersRepositoryImpl
import com.nirwashh.rickandmortyapp.characters.domain.CharactersRepository
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun provideCharactersRepository(
        service: CharactersService,
        //dao: CharactersDao
    ): CharactersRepository = CharactersRepositoryImpl(service/*, dao*/)
}