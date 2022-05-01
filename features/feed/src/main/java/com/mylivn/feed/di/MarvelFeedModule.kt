package com.mylivn.feed.di

import androidx.lifecycle.ViewModel
import com.mylivn.commons.ui.utils.ViewModelKey
import com.mylivn.domain.di.scopes.FeatureScope
import com.mylivn.feed.MarvelFeedViewModel
import com.mylivn.feed.adapter.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
interface MarvelFeedModule {

    @Binds
    @IntoMap
    @ViewModelKey(MarvelFeedViewModel::class)
    fun bindSplashViewModel(viewModel: MarvelFeedViewModel): ViewModel

    companion object {
        @FeatureScope
        @Provides
        fun provideCharacterAdapter() = CharacterAdapter()

        @FeatureScope
        @Provides
        fun provideComicAdapter() = ComicAdapter()


        @FeatureScope
        @Provides
        fun provideSeriesAdapter() = SeriesAdapter()

        @FeatureScope
        @Provides
        fun provideEventAdapter() = EventAdapter()

        @FeatureScope
        @Provides
        fun provideStoriesAdapter() = StoriesAdapter()

    }
}