package com.mylivn.data.repositories

import com.mylivn.data.BuildConfig
import com.mylivn.data.api.services.MarvelApiService
import com.mylivn.data.api.util.ApiHelper
import com.mylivn.data.mappers.SeriesMapper
import com.mylivn.data.util.Extension.Companion.md5
import com.mylivn.domain.models.base.Result
import com.mylivn.domain.models.series.Series
import com.mylivn.domain.repositories.SeriesRepository
import javax.inject.Inject


class SeriesRepositoryImpl @Inject constructor(
    private val apiHelper: ApiHelper,
    private val marvelApiService: MarvelApiService,
    private val seriesMapper: SeriesMapper,
) : SeriesRepository {

    override suspend fun getServerSeriesListByCharacterId(
        characterId: Int,
        offset: Int,
        pageSize: Int
    ): Result<List<Series>> {
        return apiHelper.safeExecute(
            block = {
                val ts = System.currentTimeMillis()
                val hash =
                    "${System.currentTimeMillis()}${BuildConfig.PRIVATE_API_KEY}${BuildConfig.PUBLIC_API_KEY}".md5()!!
                marvelApiService.getSeriesByCharacter(
                    ts,
                    BuildConfig.PUBLIC_API_KEY,
                    hash,
                    characterId,
                    pageSize,
                    offset
                )
            },
            transform = {
                seriesMapper.map(it.data!!)
            },
            persist = { series ->
                series.forEach { comic ->
                    comic.characterId = characterId
                }
                series
            }
        )

    }

}