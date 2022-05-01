package com.mylivn.data.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mylivn.data.BuildConfig
import dagger.Module
import dagger.Provides
import com.mylivn.domain.di.scopes.AppScope
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * This module contains the provide methods for retrofit & OkHttp
 */
@Module
class NetworkModule {

    @AppScope
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val okHttpBuilder = OkHttpClient.Builder()
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpBuilder.addInterceptor(loggingInterceptor)
        okHttpBuilder.retryOnConnectionFailure(true)
        okHttpBuilder.readTimeout(30, TimeUnit.SECONDS)
        okHttpBuilder.connectTimeout(30, TimeUnit.SECONDS)
        return okHttpBuilder.build()
    }

    @AppScope
    @Provides
    fun provideNexusGson(): Gson {
        return GsonBuilder()
            .setDateFormat("yyyy-MM-dd")
            .serializeNulls()
            .create()
    }



    @AppScope
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

}