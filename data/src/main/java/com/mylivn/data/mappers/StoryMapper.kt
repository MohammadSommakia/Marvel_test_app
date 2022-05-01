package com.mylivn.data.mappers

import com.mylivn.data.models.response.StoriesResponse
import com.mylivn.domain.models.story.Story
import javax.inject.Inject


class StoryMapper @Inject constructor() : BaseMapper<StoriesResponse, List<Story>> {


    override fun map(response: StoriesResponse): List<Story> {
        val storiesList = arrayListOf<Story>()
        response.results.forEach {
            storiesList.add(
                Story(
                    name = it.title,
                    id = it.id,
                    characterId = 0,
                    description = it.description,
                    imagePath = it.thumbnail.path,
                )
            )
        }
        return storiesList
    }
}