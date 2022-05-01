package com.mylivn.test.di

import com.mylivn.feed.di.MarvelFeedFragmentProvider
import com.mylivn.test.MarvelActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(
        modules = [
            MarvelFeedFragmentProvider::class,
        ]

    )
    abstract fun mainActivity(): MarvelActivity

}