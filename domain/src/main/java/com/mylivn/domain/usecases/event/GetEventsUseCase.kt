package com.mylivn.domain.usecases.event

import com.mylivn.domain.models.base.Result
import com.mylivn.domain.models.event.Event
import com.mylivn.domain.repositories.EventsRepository
import com.mylivn.domain.usecases.base.BaseNetworkUseCase
import com.mylivn.domain.usecases.base.UseCaseParameters
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject


class GetEventsUseCase @Inject constructor(
    private val eventsRepository: EventsRepository
) :
    BaseNetworkUseCase<List<Event>, GetEventsUseCase.Params>() {


    data class Params(
        val characterId: Int,
        val offset: Int,
    ) : UseCaseParameters

    override suspend fun FlowCollector<Result<List<Event>>>.run(params: Params) {
        eventsRepository.getServerEventsListByCharacterId(
            params.characterId,
            offset = params.offset
        )


    }


}