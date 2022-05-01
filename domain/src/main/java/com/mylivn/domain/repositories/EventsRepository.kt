package com.mylivn.domain.repositories

import com.mylivn.domain.models.base.Result
import com.mylivn.domain.models.event.Event


interface EventsRepository {

    suspend fun getServerEventsListByCharacterId(
        characterId: Int,
        offset: Int,
        pageSize: Int = 10
    ): Result<List<Event>>

}