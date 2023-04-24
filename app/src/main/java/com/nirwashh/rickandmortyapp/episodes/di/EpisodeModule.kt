package com.nirwashh.rickandmortyapp.episodes.di

import dagger.Module

@Module(includes = [EpisodeDataModule::class, EpisodeDomainModule::class, EpisodeApiModule::class])
class EpisodeModule