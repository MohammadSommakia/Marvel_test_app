package com.mylivn.domain.usecases.story

import com.mylivn.domain.models.base.Result
import com.mylivn.domain.models.story.Story
import com.mylivn.domain.repositories.StoriesRepository
import com.mylivn.domain.usecases.base.BaseNetworkUseCase
import com.mylivn.domain.usecases.base.UseCaseParameters
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject


class GetStoriesUseCase @Inject constructor(
    private val storiesRepository: StoriesRepository
) :
    BaseNetworkUseCase<List<Story>, GetStoriesUseCase.Params>() {


    data class Params(
        val characterId: Int,
        val offset: Int,
    ) : UseCaseParameters

    override suspend fun FlowCollector<Result<List<Story>>>.run(params: Params) {

        storiesRepository.getServerStoriesListByCharacterId(
            params.characterId,
            offset = params.offset
        )
    }


}