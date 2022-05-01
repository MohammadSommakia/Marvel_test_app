package com.mylivn.data.di

import com.mylivn.data.api.services.MarvelApiService
import com.mylivn.domain.di.scopes.AppScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * This module has the API services to fetch the data
 */
@Module(includes = [NetworkModule::class])
class ApiModule {

    @AppScope
    @Provides
    fun provideMylivnApiService( retrofit: Retrofit): MarvelApiService {
        return retrofit.create(MarvelApiService::class.java)
    }


}