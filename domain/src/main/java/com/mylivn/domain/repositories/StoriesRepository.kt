package com.mylivn.domain.repositories

import com.mylivn.domain.models.base.Result
import com.mylivn.domain.models.comic.Comic
import com.mylivn.domain.models.story.Story

interface StoriesRepository {

    suspend fun getServerStoriesListByCharacterId(
        characterId: Int,
        offset: Int,
        pageSize: Int = 10
    ): Result<List<Story>>

}