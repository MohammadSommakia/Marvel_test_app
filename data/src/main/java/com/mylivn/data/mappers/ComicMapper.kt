package com.mylivn.data.mappers

import com.mylivn.data.models.response.ComicsResponse
import com.mylivn.domain.models.comic.Comic
import javax.inject.Inject

class ComicMapper @Inject constructor() : BaseMapper<ComicsResponse, List<Comic>> {
    override fun map(response: ComicsResponse): List<Comic> {

        val comicsList = arrayListOf<Comic>()
        response.results.forEach {
            comicsList.add(
                Comic(
                    name = it.title,
                    id = it.id,
                    characterId = 0,
                    description = it.description,
                    imagePath = it.thumbnail.path,
                )
            )
        }
        return comicsList
    }
}