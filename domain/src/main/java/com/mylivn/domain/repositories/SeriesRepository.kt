package com.mylivn.domain.repositories

import com.mylivn.domain.models.base.Result
import com.mylivn.domain.models.series.Series


interface SeriesRepository {

    suspend fun getServerSeriesListByCharacterId(
        characterId: Int,
        offset: Int,
        pageSize: Int = 10
    ): Result<List<Series>>

}