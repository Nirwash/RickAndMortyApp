package com.nirwashh.rickandmortyapp.core.di

import android.content.Context
import androidx.room.Room
import com.nirwashh.rickandmortyapp.core.data.local.RickAndMortyDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Singleton
    @Provides
    fun providesRoomDatabase(context: Context) =
        Room.databaseBuilder(context, RickAndMortyDatabase::class.java, "rickAndMorty.db").build()

    @Singleton
    @Provides
    fun providesDao(db: RickAndMortyDatabase) = db.dao()
}