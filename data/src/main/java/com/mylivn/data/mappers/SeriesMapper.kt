package com.mylivn.data.mappers

import com.mylivn.data.models.response.SeriesResponse
import com.mylivn.domain.models.series.Series
import javax.inject.Inject


class SeriesMapper @Inject constructor() : BaseMapper<SeriesResponse, List<Series>> {


    override fun map(response: SeriesResponse): List<Series> {
        val seriesList = arrayListOf<Series>()
        response.results.forEach {
            seriesList.add(
                Series(
                    name = it.title,
                    id = it.id,
                    characterId = 0,
                    description = it.description,
                    imagePath = it.thumbnail.path,
                )
            )
        }
        return seriesList
    }
}