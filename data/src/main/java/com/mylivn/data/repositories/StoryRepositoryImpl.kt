package com.mylivn.data.repositories

import com.mylivn.data.BuildConfig
import com.mylivn.data.api.services.MarvelApiService
import com.mylivn.data.api.util.ApiHelper
import com.mylivn.data.mappers.StoryMapper
import com.mylivn.data.util.Extension.Companion.md5
import com.mylivn.domain.models.base.Result
import com.mylivn.domain.models.story.Story
import com.mylivn.domain.repositories.StoriesRepository
import javax.inject.Inject


class StoryRepositoryImpl @Inject constructor(
    private val apiHelper: ApiHelper,
    private val marvelApiService: MarvelApiService,
    private val storyMapper: StoryMapper,
) : StoriesRepository {

    override suspend fun getServerStoriesListByCharacterId(
        characterId: Int,
        offset: Int,
        pageSize: Int
    ): Result<List<Story>> {
        return apiHelper.safeExecute(
            block = {
                val ts = System.currentTimeMillis()
                val hash =
                    "${System.currentTimeMillis()}${BuildConfig.PRIVATE_API_KEY}${BuildConfig.PUBLIC_API_KEY}".md5()!!
                marvelApiService.getStoriesByCharacter(
                    ts,
                    BuildConfig.PUBLIC_API_KEY,
                    hash,
                    characterId,
                    pageSize,
                    offset
                )
            },
            transform = {
                storyMapper.map(it.data!!)
            },
            persist = { stories ->
                stories.forEach { comic ->
                    comic.characterId = characterId
                }
                stories
            }
        )

    }

}