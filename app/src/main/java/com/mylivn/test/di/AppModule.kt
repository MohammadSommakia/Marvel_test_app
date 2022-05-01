package com.mylivn.test.di

import android.content.Context
import com.mylivn.test.MarvelApp
import dagger.Binds
import dagger.Module
import dagger.Provides


@Module
class AppModule {

    /**
     * Create a provider method binding for [Context].
     *
     * @param application Sample Application.
     * @return Instance of context.
     * @see Binds
     */
    @Provides
    fun provideContext(application: MarvelApp): Context {
        return application.applicationContext
    }

}
