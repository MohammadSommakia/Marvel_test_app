package com.mylivn.feed.di

import com.mylivn.domain.di.scopes.FeatureScope
import com.mylivn.feed.MarvelFeedFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
interface MarvelFeedFragmentProvider {

    @FeatureScope
    @ContributesAndroidInjector(
        modules = [
            MarvelFeedModule::class
        ]
    )
    fun contributeMarvelFeedFragment(): MarvelFeedFragment

}