package com.mylivn.data.di

import com.mylivn.data.repositories.*
import com.mylivn.domain.repositories.*
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {


    @Binds
    fun bindCharacterRepository(repositoryImpl: CharacterRepositoryImpl): CharacterRepository

    @Binds
    fun bindComicsRepository(repositoryImpl: ComicsRepositoryImpl): ComicsRepository

    @Binds
    fun bindStoriesRepository(repositoryImpl: StoryRepositoryImpl): StoriesRepository

    @Binds
    fun bindSeriesRepository(repositoryImpl: SeriesRepositoryImpl): SeriesRepository

    @Binds
    fun bindEventsRepository(repositoryImpl: EventRepositoryImpl): EventsRepository

}