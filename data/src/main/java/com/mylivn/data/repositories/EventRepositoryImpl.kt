package com.mylivn.data.repositories

import com.mylivn.data.BuildConfig
import com.mylivn.data.api.services.MarvelApiService
import com.mylivn.data.api.util.ApiHelper
import com.mylivn.data.mappers.EventMapper
import com.mylivn.data.util.Extension.Companion.md5
import com.mylivn.domain.models.base.Result
import com.mylivn.domain.models.event.Event
import com.mylivn.domain.repositories.EventsRepository
import javax.inject.Inject

class EventRepositoryImpl @Inject constructor(
    private val apiHelper: ApiHelper,
    private val marvelApiService: MarvelApiService,
    private val eventMapper: EventMapper,
) : EventsRepository {
    override suspend fun getServerEventsListByCharacterId(
        characterId: Int,
        offset: Int,
        pageSize: Int
    ): Result<List<Event>> {


        return apiHelper.safeExecute(
            block = {
                val ts = System.currentTimeMillis()
                val hash =
                    "${System.currentTimeMillis()}${BuildConfig.PRIVATE_API_KEY}${BuildConfig.PUBLIC_API_KEY}".md5()!!
                marvelApiService.getEventsByCharacter(
                    ts,
                    BuildConfig.PUBLIC_API_KEY,
                    hash,
                    characterId,
                    pageSize,
                    offset
                )
            },
            transform = {
                eventMapper.map(it.data!!)
            },
            persist = { events ->
                events.forEach { comic ->
                    comic.characterId = characterId
                }
                events
            }
        )
    }

}