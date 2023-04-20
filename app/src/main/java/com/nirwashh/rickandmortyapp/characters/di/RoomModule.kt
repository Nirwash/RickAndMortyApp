package com.nirwashh.rickandmortyapp.characters.di

import android.app.Application
import androidx.room.Room
import com.nirwashh.rickandmortyapp.characters.data.cache.CharactersDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RoomModule {

    @Singleton
    @Provides
    fun providesRoomDatabase(app: Application) =
        Room.databaseBuilder(app, CharactersDatabase::class.java, "demo-db").build()

    @Singleton
    @Provides
    fun providesCharactersDao(db: CharactersDatabase) = db.charactersDao()
}