package com.mylivn.domain.usecases.series

import com.mylivn.domain.models.base.Result
import com.mylivn.domain.models.series.Series
import com.mylivn.domain.repositories.SeriesRepository
import com.mylivn.domain.usecases.base.BaseNetworkUseCase
import com.mylivn.domain.usecases.base.UseCaseParameters
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject


class GetSeriesUseCase @Inject constructor(
    private val seriesRepository: SeriesRepository
) :
    BaseNetworkUseCase<List<Series>, GetSeriesUseCase.Params>() {


    data class Params(
        val characterId: Int,
        val offset: Int,
    ) : UseCaseParameters

    override suspend fun FlowCollector<Result<List<Series>>>.run(params: Params) {
        seriesRepository.getServerSeriesListByCharacterId(
            params.characterId,
            offset = params.offset
        )

    }


}