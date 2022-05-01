package com.mylivn.data.mappers

import com.mylivn.data.models.response.EventResponse
import com.mylivn.domain.models.event.Event
import javax.inject.Inject


class EventMapper @Inject constructor() : BaseMapper<EventResponse, List<Event>> {

    override fun map(response: EventResponse): List<Event> {
        val eventsList = arrayListOf<Event>()
        response.results.forEach {
            eventsList.add(
                Event(
                    name = it.title,
                    id = it.id,
                    characterId = 0,
                    description = it.description,
                    imagePath = it.thumbnail.path,
                )
            )
        }
        return eventsList
    }
}