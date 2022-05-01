package com.mylivn.data.api.services

import com.mylivn.data.models.MarvelResponse
import com.mylivn.data.models.response.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApiService {

    //region:characters

    @GET("v1/public/characters")
    suspend fun getCharacters(
        @Query("ts") ts: Long,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Response<MarvelResponse<CharacterResponse>>
    //endregion

    //region:comics
    @GET("v1/public/characters/comics")
    suspend fun getComicsByCharacter(
        @Query("ts") ts: Long,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("characterId") characterId: Int,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int

    ): Response<MarvelResponse<ComicsResponse>>
    //endregion


    //region:events
    @GET("v1/public/characters/events")
    suspend fun getEventsByCharacter(
        @Query("ts") ts: Long,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("characterId") characterId: Int,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int

    ): Response<MarvelResponse<EventResponse>>
    //endregion


    //region:series
    @GET("v1/public/characters/series")
    suspend fun getSeriesByCharacter(
        @Query("ts") ts: Long,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("characterId") characterId: Int,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int

    ): Response<MarvelResponse<SeriesResponse>>
    //endregion


    //region:stories
    @GET("v1/public/characters/stories")
    suspend fun getStoriesByCharacter(
        @Query("ts") ts: Long,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("characterId") characterId: Int,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int

    ): Response<MarvelResponse<StoriesResponse>>
    //endregion


}