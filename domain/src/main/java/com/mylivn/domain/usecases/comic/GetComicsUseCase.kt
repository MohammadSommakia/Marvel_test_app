package com.mylivn.domain.usecases.comic

import com.mylivn.domain.models.base.Result
import com.mylivn.domain.models.comic.Comic
import com.mylivn.domain.repositories.ComicsRepository
import com.mylivn.domain.usecases.base.BaseNetworkUseCase
import com.mylivn.domain.usecases.base.UseCaseParameters
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class GetComicsUseCase @Inject constructor(
    private val comicsRepository: ComicsRepository
) :
    BaseNetworkUseCase<List<Comic>, GetComicsUseCase.Params>() {


    data class Params(
        val characterId: Int,
        val offset: Int,
    ) : UseCaseParameters

    override suspend fun FlowCollector<Result<List<Comic>>>.run(params: Params) {

        comicsRepository.getServerComicsListByCharacterId(
            params.characterId,
            offset = params.offset
        )
    }


}